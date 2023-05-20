
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

def print_record_info(records, error_records, filename):
    print(f"\n** Loaded ({len(records)}) records from the file ({filename})  **\n")
    print(f"** Found ({len(records) - len(error_records)}) valid and ({len(error_records)}) invalid transaction records ** \n")

def print_errors(error_messages):
    for line_number, list_of_errors_on_this_line in error_messages.items():
        for error_message in list_of_errors_on_this_line:
            print(f"Error at Line {line_number}: {error_message}")

def print_table_info(records, error_records):
    print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format("Machine ID", "Requested Notes", "Deposit Amount", "Status"))
    print("--------------------------------------------------------")
    for record in records:
        if record not in error_records:
            print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format(record[0], record[1], record[2], record[3]))

def print_welcome_message():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")

def validate_record(line, i, error_records, error_messages, status_tracker): #Verify each lines meets the conditions, if not then store them as an error line
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

def write_results(filename, records, error_records, error_messages): #Handle how to display the results: 
    print_record_info(records, error_records, filename)
    print_table_info(records, error_records)
    print("\n")
    print_errors(error_messages)

def main():
    error_messages = {} #Where errors get reported
    records = [] #Where every record gets stored
    error_records = [] #Where only the records that have an error get stored
    i = 0 #Number of iterations
    incorrect_format = False #Flag for incorrectly formatted file
    status_tracker = [] #Tracks the statuses of records
    print_welcome_message()
    filename = input('Enter the file name: ')
    try:
        with open(filename, 'r') as file:
            lines = file.readlines()
            for line in lines: #Do this loop on every line within this file
                i += 1
                record = process_line(line, records) #Breaks up line into an array separated by "|"
                if len(record) != 4: #Checks that the record has all four fields
                    print(f"\n** Error: file ({filename}) is incorrectly formatted **\n")
                    incorrect_format = True
                    break
                validate_record(record, i, error_records, error_messages) #Checks that all the conditions are met           
            if not lines: #Close the file once there are no more lines
                return            
            if not incorrect_format:
                write_results(filename, records, error_records, error_messages) #Write the results   
    except FileNotFoundError: #Print error if the file cannot be found
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

main()