def validate_machine_id(record):
    machine_id = record[0].upper()
    #Verify machine ID length is 6, first 3 characters are digits and last 3 characters are letters
    return len(machine_id) == 6 and machine_id[:3].isdigit() and machine_id[3:].isalpha()

def validate_requested_notes(record):
    requested_notes = record[1].split(':') #Split requested notes field by ":"
    #Verify requested notes contains 5 items and that they are digits
    return len(requested_notes) == 5 and all(note.isdigit() for note in requested_notes)

def validate_deposit(record):
    try:
        int(record[2]) #Try converting deposit field to integer
        return True #If successful, return True
    except ValueError: #If failed ValueError is raised and return False
        return False
    
def validate_status(record):
    if len(record) >= 4 and isinstance(record[3], str): #Verify record has at least 4 fields and the status field is a string
        status = record[3].upper()
        return status == "S" or status == "F" #Check if the status is either successful or a fail
    else:
        return False

def validate_record(file_info, i): 
    record = file_info['records'][-1]
    for key, error_info in file_info["error_info"].items(): #Iterate over each error in the "error_info" dictionary
        if not error_info["validator"](record): #Apply the corresponding validation function to the record for each error
            error_info["error_lines"].append(i) #If the validation fails, track the line number
            if record not in file_info["error_records"]: #If record is not already in the error records
                file_info["error_records"].append(record) #Add the invalid record to the list of error records

def print_errors(file_info):
    all_errors = [] #Empty list to hold all error messages
    for error_type, error_data in file_info["error_info"].items(): #Iterate over each error type and corresponding data in the file_info dictionary
        for line_number in error_data["error_lines"]: #Go through lines that have an error, 
            all_errors.append((line_number, error_data['message'])) #Append a tuple of the line number and error message to the all_errors list
    all_errors.sort() #Sort the error messages by line number from lowest to highest line number
    for line_number, message in all_errors: #Print the error messages with their corresponding line numbers
        print(f"Error at Line {line_number}: {message}")

def print_record_info(file_info):
    record_count = len(file_info['records']) #Total number of records
    error_count = len(file_info['error_records']) #Total number of error records
    valid_count = record_count - error_count #Number of valid records
    print(f"\n** Loaded ({record_count}) records from the file ({file_info['name']})  **\n") #Print total records and file name
    print(f"** Found ({valid_count}) valid and ({error_count}) invalid transaction records ** \n") #Print valid and error records count

def print_table_info(file_info):
    print("| {:^10s} | {:^15s} | {:^15s} | {:^7s} |".format("Machine ID", "Requested Notes", "Deposit Amount", "Status")) #Print the table header
    print("------------------------------------------------------------")
    for record in file_info['records']: #For every record,
        if record not in file_info['error_records']: #that is not an invalid record,
            print("| {:^10s} | {:^15s} | {:^15s} | {:^7s} |".format(record[0], record[1], record[2], record[3])) #display row in the table

def print_welcome_message():
    print("----------------------")
    print("WELCOME TO UTOPIA BANK")
    print("----------------------\n")

def print_statuses(file_info):
    transactions = file_info["transactions"]
    print("\n** Transaction statistics **")
    print(f"\n- Successful transactions: {transactions['successful']}") #Print number of successful transactions
    print(f"- Failed transactions: {transactions['failed']}") #Print number of unsuccessful transactions

def status_counter(file_info):
    record = file_info['records'][-1]
    status = record[3].upper()
    error_records = file_info["error_records"]
    transactions = file_info["transactions"]
    if record not in error_records: #Only proceed if the record is valid
        if status == "S": #If the status is "S" (successful)
            transactions["successful"] += 1 #Increment the count of successful transactions
        else: #Otherwise,
            transactions["failed"] += 1 #Increment the count of failed transactions

def note_counter(file_info):
    record = file_info['records'][-1]
    note_keys = ['n_5', 'n_10', 'n_20', 'n_50', 'n_100'] #Define the keys for each type of note
    if record not in file_info['error_records']: #Only proceed if the record is valid
        notes = [int(item) for item in record[1].split(":")] #Split the records requested notes field by ":" and convert into integers
        for note_key, note_count in zip(note_keys, notes): #Pair each note key with the corresponding requested notes
            file_info['notes'][note_key] += note_count #Add a count to the total for that note in the 'notes' dictionary

def print_requested_notes(file_info): #Displays qty of each note requested
    note = file_info['notes']
    print("- Total requested notes:") 
    print(f"$5   ({note['n_5']})")
    print(f"$10  ({note['n_10']})")
    print(f"$20  ({note['n_20']})")
    print(f"$50  ({note['n_50']})")
    print(f"$100 ({note['n_100']})\n")

def process_line(line): #Break up each record into a separate array with each field separated by "|"
    return [item.strip() for item in line.split('|')]

def write_results(file_info):
    print_record_info(file_info) #Prints total number of records, file name, valid and error record count
    print_table_info(file_info) #Prints table showing all the valid records
    print_statuses(file_info) #Prints number of successful and failed transactions out of the valid records
    print_requested_notes(file_info) #Prints qty of each requested note out of the valid records
    print_errors(file_info) #Prints the lines that have errors with a error message and what line they occur on from smallest to largest
    print("\n")

def main():
    print_welcome_message()

    file_info = { #Dictionary to store information about the file and records
        "name": "placeholder", #Name of the file entered by the user
        "records": [], #Where every record gets stored (valid and error records)
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
        "transactions": { #Tracks the amount of successful and failed transactions
            "successful": 0,
            "failed": 0
        },
        "notes": { #Tracks how many of each note is requested
            "n_5": 0,
            "n_10": 0,
            "n_20": 0,
            "n_50": 0,
            "n_100": 0
        }
    }
    i = 0 #Number of iterations    
    file_info['name'] = input('Enter the file name: ')

    try: #Try to open the file with the name provided by the user
        with open(file_info['name'], 'r') as file: #Using 'with' ensures that the file is automatically closed once we're done with it, even if an error occurs
            lines = file.readlines()

            for line in lines: #Go through every line within the file
                i += 1 #Increase the counter to track what line number we are iterating through

                record = process_line(line) #Breaks up line into an array separated by "|"
                file_info["records"].append(record) #Sends record into the records array

                if len(record) != 4: #Checks that the record has all four fields
                    print(f"\n** Error: file ({file_info['name']}) is incorrectly formatted **\n") #If record does not have four fields print error message and then,
                    file_info["incorrect_format"] = True #Flag that the format is incorrect
                    break #Break out of the loop

                validate_record(file_info, i) #Verify if the record meets all the conditions
                note_counter(file_info) #Counts the requested notes
                status_counter(file_info) #Counts successful and failed statuses
       
        if not file_info["incorrect_format"]: #If loop was not broken by an incorrect format being flagged:
            write_results(file_info) #Write the results

    except FileNotFoundError: #Print error if the file cannot be found
        print(f"\n** Error: cannot find the file ({file_info['name']})**")

main()
