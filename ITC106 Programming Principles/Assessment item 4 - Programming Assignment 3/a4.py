
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

def check_requested_notes(record):
    requested_notes = record[1].split(':')
    return len(requested_notes) == 5 and all(note.isdigit() for note in requested_notes)

def print_record_info(records, error_records, filename, f=None):
    print(f"\n** Loaded ({len(records)}) records from the file ({filename})  **\n", file=f)
    print(f"** Found ({len(records) - len(error_records)}) valid and ({len(error_records)}) invalid transaction records ** \n", file=f)

def print_errors(error_messages, f=None):
    for line_number, list_of_errors_on_this_line in error_messages.items():
        for error_message in list_of_errors_on_this_line:
            print(f"Error at Line {line_number}: {error_message}", file=f)

def print_table_info(records, error_records, f=None):
    print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format("Machine ID", "Requested Notes", "Deposit Amount", "Status"), file=f)
    print("--------------------------------------------------------", file=f)
    for record in records:
        if record not in error_records:
            print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format(record[0], record[1], record[2], record[3]), file=f)

def print_file_name(filename, f):
    print(f"\n↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ RESULTS FOR {filename} ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", file=f)

def print_welcome_message():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")
    print('Type "A" to process all files')
    print('Type "B" to process a specific file\n')

def main():
    print_welcome_message()
    choice = input('"A" or "B": ')
    results_file = "results.txt"
    if choice.upper() == 'B':
        filename = input("\nEnter the name of the file you want to process: ")
        process_file(filename, None)
    elif choice.upper() == 'A':
        all_files = ['n1.txt', 'n2.txt', 'n3.txt', 'n4.txt', 'n5.txt']
        for filename in all_files:
            process_file(filename, results_file)
        print(f'\nResults file created. Please check "{results_file}".\n')
    else:
        print("Invalid choice. Please enter 'specific' or 'all'.")

def process_file(filename, results_file):
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
                #Verify each lines meets the conditions, if not then store them as an error line
                if len(split_line) != 4:
                    handle_error(split_line, i, error_records, error_messages, "Each record must have 4 fields.")
                if not check_machine_id(split_line):
                    handle_error(split_line, i, error_records, error_messages, "Machine ID must be three digits followed by three letters.")
                if not check_requested_notes(split_line):
                    handle_error(split_line, i, error_records, error_messages, "Requested notes must be five numbers separated by colons.")
            #Close the file once there are no more lines
            if not lines:
                return           
            #Handle how to display the results: 
            #If the user decided to run all the files, then it will display the results in another file
            #If the user chose to run a specific file, then it will display the results in the terminal
            if results_file is not None:
                with open(results_file, 'a') as f:
                    print_file_name(filename, f)
                    #Print how many records were loaded and how many of those are valid and invalid
                    print_record_info(records, error_records, filename, f)
                    #Print a 2D table of the valid records
                    print_table_info(records, error_records, f)
                    print("\n", file=f)
                    #Print error info
                    print_errors(error_messages, f)
            else:
                #Print how many records were loaded and how many of those are valid and invalid
                print_record_info(records, error_records, filename)
                #Print a 2D table of the valid records
                print_table_info(records, error_records)
                print("\n")
                #Print error info
                print_errors(error_messages)
    except FileNotFoundError:
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

main()