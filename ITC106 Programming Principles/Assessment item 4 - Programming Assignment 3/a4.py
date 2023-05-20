
def handle_error(record, line_number, error_records, error_messages, message):
    if record not in error_records:
        error_records.append(record)
    if line_number in error_messages:
        error_messages[line_number].append(message)
    else:
        error_messages[line_number] = [message]

def check_machine_id(record):
    machine_id = record[0].upper()
    return len(machine_id) == 6 and machine_id[:3].isdigit() and machine_id[3:].isalpha()

def print_record_info(records, error_records, filename):
    print(f"\n** Loaded ({len(records)}) records from the file ({filename})  **\n")
    print(f"** Found ({len(records) - len(error_records)}) valid and ({len(error_records)}) invalid transaction records ** \n")

def print_errors(error_messages):
    for line_number, list_of_errors_on_this_line in error_messages.items():
        for error_message in list_of_errors_on_this_line:
            print(f"Error at Line {line_number}: {error_message}")

def main():
    filename = input("Enter the file name: ")
    error_messages = {}
    records = []
    error_records = []
    i = 0

    try:
        with open(filename, 'r') as file:

            lines = file.readlines()

            for line in lines:
                i += 1

                #Break up each record into separate arrays with each field separated by "|"
                split_line = [item.strip() for item in line.split('|')]
                records.append(split_line)

                #Check if there are four columns
                if len(split_line) != 4:
                    handle_error(split_line, i, error_records, error_messages, "Each record must have 4 fields.")
                if not check_machine_id(split_line):
                    handle_error(split_line, i, error_records, error_messages, "Machine ID must be three digits followed by three letters.")

            #Close the file once there are no more lines
            if not lines:
                return
            
            #Print how many records were loaded and how many of those are valid and invalid
            print_record_info(records, error_records, filename)

            #Print a 2D table of the valid records
            #Note: personally I would have put this into it's own function and then have that called
            #but due to the assignment constraint "Part 1 must be implemented in the main function." I have left it here:
            print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format("Machine ID", "Requested Notes", "Deposit Amount", "Status"))
            print("--------------------------------------------------------")
            for record in records:
                if record not in error_records:
                    print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format(record[0], record[1], record[2], record[3]))

            print("\n")

            #Print error info
            print_errors(error_messages)
            

    except FileNotFoundError:
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

main()