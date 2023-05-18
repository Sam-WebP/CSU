
def check_machine_id(record, line_number, error_records, error_messages):
    machine_id = record[0].upper()
    if len(machine_id) != 6 or not machine_id[:3].isdigit() or not machine_id[3:].isalpha():
        error_records.append(record)
        error_messages[line_number] = "Machine ID must be three digits followed by three letters."
    return

def print_record_info(records, error_records, filename):
    print(f"\n** Loaded ({len(records)}) records from the file ({filename})  **\n")
    print(f"** Found ({len(records) - len(error_records)}) valid and ({len(error_records)}) invalid transaction records ** \n")

def print_records(records, error_records):
    print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format("Machine ID", "Requested Notes", "Deposit Amount", "Status"))
    print("--------------------------------------------------------")
    for record in records:
        if record not in error_records:
            print("{:^10s} | {:^15s} | {:^15s} | {:^7s}".format(record[0], record[1], record[2], record[3]))

def print_errors(error_messages):
    for line_number, error_message in error_messages.items():
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
                    error_records.append(split_line)
                    error_messages[i] = "Each record must have 4 fields."
                
                check_machine_id(split_line, i, error_records, error_messages)

            #Close the file once there are no more lines
            if not lines:
                return
            
            #Print how many records were loaded and how many of those are valid and invalid
            print_record_info(records, error_records, filename)

            #Print a 2D table of the valid records
            print_records(records, error_records)
            print("\n")

            #Print error info
            print_errors(error_messages)
            

    except FileNotFoundError:
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")

main()