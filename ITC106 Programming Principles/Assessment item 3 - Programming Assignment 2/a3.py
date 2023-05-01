# Custom exception class to raise when there is an error during transaction processing
class TransactionProcessingError(Exception):
    pass

# This function reads a single transaction from the given lines starting at index i
# It checks each line for a valid positive integer, and returns the valid results and any errors
def read_transaction(lines, i):
    errors = []
    notes = []
    for j in range(i, min(i+5, len(lines))):
        result = check_positive_integer(lines[j].strip(), j+1)
        if isinstance(result, str):
            errors.append(result)
        else:
            notes.append(result)
    return notes, errors

# This function checks if a value is a positive integer or 0
# It also checks for invalid values such as those containing commas or empty strings
def check_positive_integer(value, line_number):
    if ',' in value:
        return f'Invalid value: "{value}" on line {line_number}. Expected a single number per line.'
    elif value.isdigit():
        return int(value)
    elif value == "":
        return f'Invalid value: "{value}" on line {line_number}. Value cannot be empty.'
    else:
        return f'Invalid value: "{value}" on line {line_number}. Expected a non-negative whole integer.'

# This function processes all transactions in the given lines and returns a list of transactions
# It reads each transaction, collects any errors, and raises an exception if there are any errors
def process_transactions(lines):
    transactions = []
    invalid_lines = []
    for i in range(0, len(lines), 5):
        transaction, errors = read_transaction(lines, i)
        if errors:
            invalid_lines.extend(errors)
        else:
            transactions.append(tuple(transaction))
    if invalid_lines:
        raise TransactionProcessingError("\n".join(invalid_lines))
    return transactions

# This function calculates the total dollar amount of a transaction given the number of notes for each value
def calculate_amount(note_5, note_10, note_20, note_50, note_100):
    return note_5 * 5 + note_10 * 10 + note_20 * 20 + note_50 * 50 + note_100 * 100

# This function displays the change statistics given a list of total notes
def display_statistics(total_notes):
    print("\n** Change statistics **\n")
    print("Value     Count")
    print("---------------")
    for idx, note in enumerate([5, 10, 20, 50, 100]):
        print(f"${note:<9}{total_notes[idx]:<5}")


# The main function that drives the program
# It reads a file of transactions, processes each transaction, calculates the total amount, 
# and displays the change statistics for all transactions
def main():
    # Print a welcome message
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")

    # Ask for the name of the file containing the transactions
    filename = input("Enter the file name: ")

    try:
        # Attempt to open the file in read mode
        with open(filename, 'r') as file:
            lines = file.readlines()
            
            if not lines:
                file.close()
                return

            # If the number of lines in the file that do not contain a comma and is not divisible by 5, print an error message
            if len([line for line in lines if ',' not in line]) % 5 != 0:
                print(f"\n** Error: {filename} is missing records **")
                print("\n** Transaction processing failed. Exiting. **\n")
                file.close()
                return

            # A list to keep track of the total number of each note
            total_notes = [0, 0, 0, 0, 0]
            print("\n** Found the following transactions **\n")

            # Attempt to process the transactions in the file
            try:
                transactions = process_transactions(lines)
                
            # If there is an error during processing, print the error message and exit the program
            except TransactionProcessingError as e:
                print(e)
                print("\n** Transaction processing failed. Exiting. **\n")
                return

            # For each transaction, calculate the total amount, print the transaction details,
            # and update the total number of each note
            for note_5, note_10, note_20, note_50, note_100 in transactions:
                amount = calculate_amount(note_5, note_10, note_20, note_50, note_100)
                print(f"Amount: ${amount}. Change: $5 [{note_5}] $10 [{note_10}] $20 [{note_20}] $50 [{note_50}] $100 [{note_100}]")

                total_notes[0] += note_5
                total_notes[1] += note_10
                total_notes[2] += note_20
                total_notes[3] += note_50
                total_notes[4] += note_100

            # Display the final change statistics
            display_statistics(total_notes)

    # If the file is not found, print an error message and exit the program
    except FileNotFoundError:
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

# Run the main function
if __name__ == "__main__":
    main()