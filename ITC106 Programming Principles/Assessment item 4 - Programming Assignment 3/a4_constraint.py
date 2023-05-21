def validate_record(file_info, i): 
    record = file_info['records'][-1]
    for key, error_info in file_info["error_info"].items():
        if not error_info["validator"](record):
            error_info["error_lines"].append(i)
            file_info["error_records"].append(record)

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

def print_errors(file_info):
    all_errors = []
    for error_type, error_data in file_info["error_info"].items():
        for line_number in error_data["error_lines"]:
            all_errors.append((line_number, error_data['message']))
    all_errors.sort()
    for line_number, message in all_errors:
        print(f"Error at Line {line_number}: {message}")

def print_record_info(file_info):
    record_count = len(file_info['records'])
    error_count = len(file_info['error_records'])
    valid_count = record_count - error_count
    print(f"\n** Loaded ({record_count}) records from the file ({file_info['name']})  **\n")
    print(f"** Found ({valid_count}) valid and ({error_count}) invalid transaction records ** \n")

def print_table_info(file_info):
    print("| {:^10s} | {:^15s} | {:^15s} | {:^7s} |".format("Machine ID", "Requested Notes", "Deposit Amount", "Status"))
    print("------------------------------------------------------------")
    for record in file_info['records']:
        if record not in file_info['error_records']:
            print("| {:^10s} | {:^15s} | {:^15s} | {:^7s} |".format(record[0], record[1], record[2], record[3]))

def print_welcome_message():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")

def print_statuses(file_info):
    transactions = file_info["transactions"]
    print(f"\n- Successful transactions: {transactions['successful']}")
    print(f"- Failed transactions: {transactions['failed']}\n")

def status_counter(file_info):
    record = file_info['records'][-1]
    status = record[3].upper()
    error_records = file_info["error_records"]
    transactions = file_info["transactions"]
    if record not in error_records:
        if status == "S":
            transactions["successful"] += 1
        else:
            transactions["failed"] += 1

def note_counter(file_info):
    record = file_info['records'][-1]
    note_keys = ['n_5', 'n_10', 'n_20', 'n_50', 'n_100']
    if record not in file_info['error_records']:
        notes = [int(item) for item in record[1].split(":")]
        for note_key, note_count in zip(note_keys, notes):
            file_info['notes'][note_key] += note_count

def print_requested_notes(file_info):
    note = file_info['notes']
    print("- Total requested notes:")
    print(f"$5   ({note['n_5']})")
    print(f"$10  ({note['n_10']})")
    print(f"$20  ({note['n_20']})")
    print(f"$50  ({note['n_50']})")
    print(f"$100 ({note['n_100']})\n")

def process_line(line): #Break up each record into a separate array with each field separated by "|"
    return [item.strip() for item in line.split('|')]

def write_results(file_info): #Handle how to display the results: 
    print_record_info(file_info)
    print_table_info(file_info)
    print_statuses(file_info)
    print_requested_notes(file_info)
    print_errors(file_info)
    print("\n")

def main():
    print_welcome_message()

    file_info = {
        "name": "placeholder",
        "records": [], #Where every record gets stored
        "error_records": [], #Where only the records that have an error get stored
        "error_info": { #Tracks error line numbers, messages and validators for each field
            "machine_id": {
                "error_lines": [],
                "message": "Machine ID must be three digits followed by three letters.",
                "validator": validate_machine_id
            },
            "requested_notes": {
                "error_lines": [],
                "message": "Requested notes must be five numbers separated by colons.",
                "validator": validate_requested_notes
            },
            "deposit_amount": {
                "error_lines": [],
                "message": "Deposit amount must be an integer number.",
                "validator": validate_deposit
            },
            "status": {
                "error_lines": [],
                "message": "Status must be a single letter which is either S (Success) or F (Fail).",
                "validator": validate_status
            }
        }, 
        "incorrect_format": False, #Flag to validate of the format is correct
        "transactions": { 
            "successful": 0,
            "failed": 0
        },
        "notes": {
            "n_5": 0,
            "n_10": 0,
            "n_20": 0,
            "n_50": 0,
            "n_100": 0
        }
    }
    i = 0 #Number of iterations    
    file_info['name'] = input('Enter the file name: ')
    try:
        with open(file_info['name'], 'r') as file:
            lines = file.readlines()

            for line in lines: #Do this loop on every line within this file
                i += 1

                record = process_line(line) #Breaks up line into an array separated by "|"
                file_info["records"].append(record) #Sends record into the records array

                if len(record) != 4: #Checks that the record has all four fields
                    print(f"\n** Error: file ({file_info['name']}) is incorrectly formatted **\n")
                    file_info["incorrect_format"] = True
                    break

                validate_record(file_info, i) #Checks that all the conditions are met
                note_counter(file_info)
                status_counter(file_info)

        if not lines: #Close the file once there are no more lines
            return            
        if not file_info["incorrect_format"]:
            write_results(file_info) #Write the results  

    except FileNotFoundError: #Print error if the file cannot be found
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

main()
