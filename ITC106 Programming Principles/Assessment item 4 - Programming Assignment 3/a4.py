










def main():
    filename = input("Enter the file name: ")

    records = []
    line_count = 0

    try:
        with open(filename, 'r') as file:
            lines = file.readlines()
            
            #Count how many "|" are in the file
            for line in lines:
                line_count += line.count("|")

                #Break up each row into separate arrays with each field separated by "|"
                split_line = [item.strip() for item in line.split('|')]
                records.append(split_line)

                #Check if amount of "|" is divisible by 3 (there are no missing field separators to make up 4 field)
                #Check that the last or first character is not a "|" (there could be enough "|" but nothing in the first or last field)
                if line_count % 3 != 0 or line[-1] == "|" or line[0] == "|":
                    print("You have missing fields")
                    return

            if not lines:
                return
            
            print(f"** Loaded (8) records from the file ({file})  **")
            print(records)
            
    except FileNotFoundError:
        print("\n** Error: cannot find the file **")
        print("\n** Transaction processing failed. Exiting. **\n")











main()