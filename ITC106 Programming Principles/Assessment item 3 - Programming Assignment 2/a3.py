# Custom exception
class TransactionProcessingError(Exception):
    pass

# Reads a single transaction from the given lines starting at index i
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

# Checks if a value is a positive integer or 0
def check_positive_integer(value, line_number):
    if ',' in value:
        return f'Invalid value: "{value}" on line {line_number}. Expected a single number per line.'
    elif value.isdigit():
        return int(value)
    elif value == "":
        return f'Invalid value: "{value}" on line {line_number}. Value cannot be empty.'
    else:
        return f'Invalid value: "{value}" on line {line_number}. Expected a non-negative whole integer.'

# Processes all transactions in the given lines and returns a list of transactions
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

# Calculates the total dollar amount of a transaction given the number of notes for each value
def calculate_amount(note_5, note_10, note_20, note_50, note_100):
    return note_5 * 5 + note_10 * 10 + note_20 * 20 + note_50 * 50 + note_100 * 100

# Displays the change statistics given a list of total notes
def display_statistics(total_notes):
    print("\n** Change statistics **\n")
    print("Value     Count")
    print("---------------")
    for idx, note in enumerate([5, 10, 20, 50, 100]):
        print(f"${note:<9}{total_notes[idx]:<5}")

# If the user forgets to add ".txt" at the end of the file name add it on for them
def add_txt_extension(filename):
    if not filename.endswith(".txt"):
        return filename + ".txt"
    return filename

# The main function that drives the program
def main():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")

    # Get the file name from the user
    filename = add_txt_extension(input("Enter the file name: "))

    try:
        # Open the file and read its contents
        with open(filename, 'r') as file:
            lines = file.readlines()
            # Close the file and exit if the file is empty
            if not lines:
                file.close()
                return

            total_notes = [0, 0, 0, 0, 0]
            print("\n** Found the following transactions **\n")

            # Process the transactions and store them in a list
            try:
                transactions = process_transactions(lines)
            except TransactionProcessingError as e:
                print(e)
                print("\n** Transaction processing failed. Exiting. **\n")
                return

            # Iterate through the transactions, calculate the amount and update the total notes
            for note_5, note_10, note_20, note_50, note_100 in transactions:
                amount = calculate_amount(note_5, note_10, note_20, note_50, note_100)
                print(f"Amount: ${amount}. Change: $5 [{note_5}] $10 [{note_10}] $20 [{note_20}] $50 [{note_50}] $100 [{note_100}]")

                total_notes[0] += note_5
                total_notes[1] += note_10
                total_notes[2] += note_20
                total_notes[3] += note_50
                total_notes[4] += note_100

            # Display the change statistics
            display_statistics(total_notes)

    # Handle the case where the file is not found
    except FileNotFoundError:
        print("\n** Error: cannot read from the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

# Run the main function
if __name__ == "__main__":
    main()