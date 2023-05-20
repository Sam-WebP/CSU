
def handle_error(record, line_number, error_records, error_messages, message):
    if record not in error_records:
        error_records.append(record)
    if line_number in error_messages:
        error_messages[line_number].append(message)
    else:
        error_messages[line_number] = [message]

def validate_machine_id(record):
    machine_id = record[0].upper()
    return len(machine_id) == 6 and machine_id[:3].isdigit() and machine_id[3:].isalpha()

def validate_requested_notes(record):
    requested_notes = record[1].split(':')
    return len(requested_notes) == 5 and all(note.isdigit() for note in requested_notes)

def validate_deposit(record):
    try:
        int(record[2])
        return True
    except ValueError:
        return False
    
def validate_status(record, status_tracker):
    if len(record) >= 4 and isinstance(record[3], str):
        status = record[3].upper()
        status_tracker.append(status)
        return status == "S" or status == "F"
    else:
        return False

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

def print_statuses(status_tracker, f=None):
    s_count = 0
    f_count = 0
    for status in status_tracker:
        if status == "S":
            s_count += 1
        if status == "F":
            f_count += 1
    print(f"- Successful transactions: {s_count}", file=f)
    print(f"- Failed transactions: {f_count}", file=f)

def print_file_name(filename, f):
    print(f"\n↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ RESULTS FOR {filename} ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", file=f)

def print_welcome_message():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")
    print('Type "A" to process all files')
    print('Type "B" to process a specific file\n')

def validate_record(line, i, error_records, error_messages, status_tracker): #Verify each lines meets the conditions, if not then store them as an error line
    if len(line) != 4:
        handle_error(line, i, error_records, error_messages, "Each record must have 4 fields.")
    if not validate_machine_id(line):
        handle_error(line, i, error_records, error_messages, "Machine ID must be three digits followed by three letters.")
    if not validate_requested_notes(line):
        handle_error(line, i, error_records, error_messages, "Requested notes must be five numbers separated by colons.")
    if not validate_deposit(line):
        handle_error(line, i, error_records, error_messages, "Deposit amount must be an integer number.")
    if not validate_status(line, status_tracker):
        handle_error(line, i, error_records, error_messages, "Status must be a single letter which is either S (Success) or F (Fail).")

def process_line(line, records): #Break up each record into a separate array with each field separated by "|"
    split_line = [item.strip() for item in line.split('|')]
    records.append(split_line)
    return split_line

def write_results(filename, records, error_records, error_messages, results_file, status_tracker): #Handle how to display the results: 
    if results_file is not None: #If the user decided to run all the files, then it will display the results in another file 
        with open(results_file, 'a') as f:
            print_file_name(filename, f)
            print_record_info(records, error_records, filename, f)
            print_table_info(records, error_records, f)
            print("\n", file=f)
            print("** Transaction statistics **", file=f)
            print_statuses(status_tracker, f)
            print_errors(error_messages, f)
    else: #If the user chose to run a specific file, then it will display the results in the terminal
        print_record_info(records, error_records, filename)
        print_table_info(records, error_records)
        print("\n")
        print("** Transaction statistics **")
        print_statuses(status_tracker)
        print_errors(error_messages)

def process_file(filename, results_file, is_multi_file_run):
    error_messages = {} #Where errors get reported
    records = [] #Where every record gets stored
    error_records = [] #Where only the records that have an error get stored
    status_tracker = []
    i = 0 #Number of iterations
    incorrect_format = False # flag for incorrect file format
    try:
        with open(filename, 'r') as file:
            lines = file.readlines()
            for line in lines: #Do this loop on every line within this file
                i += 1
                record = process_line(line, records) #Breaks up line into an array separated by "|"
                if len(record) != 4:
                    print(f"** Error: file ({filename}) is incorrectly formatted **")
                    if not is_multi_file_run:
                        return False
                    else:
                        return None

                validate_record(record, i, error_records, error_messages, status_tracker) #Checks that all the conditions are met           
            if not lines: #Close the file once there are no more lines
                return  
            if not incorrect_format:          
                write_results(filename, records, error_records, error_messages, results_file, status_tracker) #Write the results
                return True
            
    except FileNotFoundError: #Print error if the file cannot be found
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

def main():
    print_welcome_message()
    choice = input('"A" or "B": ')
    results_file = "results.txt"
    with open(results_file, 'w'):
        pass
    if choice.upper() == 'B':
        filename = input("\nEnter the name of the file you want to process: ")
        process_file(filename, None, False)
    elif choice.upper() == 'A':
        all_files = ['n1.txt', 'n2.txt', 'n3.txt', 'n4.txt', 'n5.txt']
        for filename in all_files:
            result = process_file(filename, results_file, True)
            if result is None:
                print(f"\n** Error: file ({filename}) is incorrectly formatted. Moving to next file. **\n")
        print(f'\nResults file created. Please check "{results_file}".\n')
    else:
        print("Invalid choice. Please enter 'A' or 'B'.")

main()