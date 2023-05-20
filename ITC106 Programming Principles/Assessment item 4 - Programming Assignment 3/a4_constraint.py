def print_errors(file_info):
    all_errors = []
    for error_type, error_data in file_info["error_info"].items():
        for line_number in error_data["error_lines"]:
            all_errors.append((line_number, error_data['message']))
    all_errors.sort()
    for line_number, message in all_errors:
        print(f"Error at Line {line_number}: {message}")

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
    
def validate_status(record):
    if len(record) >= 4 and isinstance(record[3], str):
        status = record[3].upper()
        return status == "S" or status == "F"
    else:
        return False

def print_record_info(file_info):
    record_count = len(file_info['records'])
    error_count = len(file_info['error_records'])
    valid_count = record_count - error_count
    print(f"\n** Loaded ({record_count}) records from the file ({file_info['name']})  **\n")
    print(f"** Found ({valid_count}) valid and ({error_count}) invalid transaction records ** \n")

def print_table_info(file_info):
    print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format("Machine ID", "Requested Notes", "Deposit Amount", "Status"))
    print("--------------------------------------------------------")
    for record in file_info['records']:
        if record not in file_info['error_records']:
            print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format(record[0], record[1], record[2], record[3]))

def print_welcome_message():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")

def status_counter(record, file_info, error_records):
    record = file_info['records'][-1]
    status = file_info['records'][-1][3]
    error_records = file_info["error_records"]
    if isinstance(status, str) and record not in error_records:
        if status.upper() == "S":
            file_info["transactions"]["successful"] += 1
        elif status.upper() == "F":
            file_info["transactions"]["failed"] += 1

def print_statuses(file_info):
    print(f"- Successful transactions: {file_info['transactions']['successful']}")
    print(f"- Failed transactions: {file_info['transactions']['failed']}")

def validate_record(file_info, i): #Verify each lines meets the conditions, if not then store them as an error line
    line = file_info['records'][-1]
    validators = {
        "machine_id": validate_machine_id,
        "requested_notes": validate_requested_notes,
        "deposit_amount": validate_deposit,
        "status": validate_status
    }
    for key, validator in validators.items():
        if not validator(line):
            file_info["error_info"][key]["error_lines"].append(i)
            file_info["error_records"].append(line)

def process_line(line, records): #Break up each record into a separate array with each field separated by "|"
    split_line = [item.strip() for item in line.split('|')]
    records.append(split_line)
    return split_line

def write_results(file_info): #Handle how to display the results: 
    print_record_info(file_info)
    print_table_info(file_info)
    print("\n")
    print_errors(file_info)

def main():
    print_welcome_message()

    file_info = {
        "name": "placeholder",
        "records": [], #Where every record gets stored
        "error_records": [], #Where only the records that have an error get stored
        "error_info": {
            "machine_id": {
                "error_lines": [],
                "message": "Machine ID must be three digits followed by three letters."
            },
            "requested_notes": {
                "error_lines": [],
                "message": "Requested notes must be five numbers separated by colons."
            },
            "deposit_amount": {
                "error_lines": [],
                "message": "Deposit amount must be an integer number."
            },
            "status": {
                "error_lines": [],
                "message": "Status must be a single letter which is either S (Success) or F (Fail)."
            }
        }, 
        "incorrect_format": False,
        "transactions": {
            "successful": 0,
            "failed": 0
        }
    }
    i = 0 #Number of iterations    
    file_info['name'] = input('Enter the file name: ')
    try:
        with open(file_info['name'], 'r') as file:
            lines = file.readlines()
            for line in lines: #Do this loop on every line within this file
                i += 1
                record = process_line(line, file_info["records"]) #Breaks up line into an array separated by "|"
                if len(record) != 4: #Checks that the record has all four fields
                    print(f"\n** Error: file ({file_info['name']}) is incorrectly formatted **\n")
                    file_info["incorrect_format"] = True
                    break
                validate_record(file_info, i) #Checks that all the conditions are met
                status_counter(record, file_info, file_info["error_records"])        
        if not lines: #Close the file once there are no more lines
            return            
        if not file_info["incorrect_format"]:
            write_results(file_info) #Write the results   
    except FileNotFoundError: #Print error if the file cannot be found
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

main()
