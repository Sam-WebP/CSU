###This is the first exercise from Topic 1###
#weight_lbs = float(input('What is the weight in pounds is: '))
#lbs_to_kgs = weight_lbs * 0.454
#print("lbs = ", weight_lbs, " kgs = ", lbs_to_kgs, sep="")

###This is the second exercise from Topic 1###
#temp_f = float(input('What is the temperature in f: '))
#f_to_c = 5/9 * (temp_f - 32)
#print("Fahrenheit = ", temp_f, " Degree Celsius = ", f_to_c, sep="")

###This is the third exercise from Topic 1###
# original_shares = int(input("Original shares = "))
# original_price = float(input("Original price per share = "))
# original_commission_total = float(input("Original stockbroker commission percentage = ")) / 100
# total_originally_spent = original_shares * original_price
# original_dollar_value_owned = total_originally_spent - (total_originally_spent * original_commission_total)

# current_shares = int(input("Current shares = "))
# current_price = float(input("Current price per share = "))
# selling_total = current_shares * current_price
# current_commission_total = float(input("Current stockbroker commission percentage = ")) / 100
# total_after_selling = selling_total - (selling_total * current_commission_total)
# total_net_profit = total_after_selling - original_dollar_value_owned

# print("money that XYZ paid for the stock = ", total_originally_spent, sep=" ")
# print("commission that XYZ paid the broker on buying the stock = ", original_commission_total, sep=" ")
# print("amount that XYZ sold the stock for = ", current_price, sep=" ")
# print("commission that XYZ paid the broker on selling the stock = ", current_commission_total, sep=" ")
# print("net profit XYZ made in stock business = ", total_net_profit, sep=" ")

#print("Testing my commission vs chatGPT = ", selling_total * current_commission_total, sep=" ")
#print("-------------------------------------------------")
#print("original_shares = ", original_shares)
#print("original_price = ", original_price)
#print("original_commission_total = ", original_commission_total)
#print("total_originally_spent = ", total_originally_spent)
#print("original_dollar_value_owned = ", original_dollar_value_owned)

# ###This is the first exercise from Topic 2###
# number = int(input("Type in a number "))
# if number == 0:
#     print("Zero")
# elif number > 0:
#     print("Positive")
# elif number < 0:
#     print("Negative")
#     
# if number % 2 != 0:
#     print("Odd")
# else:
#     print("Even")

###This is the second exercise from Topic 2###
# word_count = int(input("Type in a number "))
# hours_needed = round(word_count / 30, 2)
# hourly_rate = float(25)
# if hours_needed > 8:
#     hourly_rate = 21.75
# labor_charges = round(float(hours_needed * hourly_rate), 2)
# 
# print(f"This will take {hours_needed} hours to complete\nThis will cost ${hourly_rate} per hour\nWhich will be a total of ${labor_charges}")


###This is the third exercise from Topic 2###
# class_a = 50
# class_b = 30
# class_c = 15
# ticket = input("What class ticket do you have: A, B or C ").upper()
# if ticket == "A":
#     print("This ticket costs $", class_a, sep="")
# elif ticket == "B":
#     print("This ticket costs $", class_b, sep="")
# elif ticket == "C":
#     print("This ticket costs $", class_c, sep="")
# else:
#     print("Invalid input. Please enter 'A', 'B' or 'C'.")

###This is the fourth exercise from Topic 2###
# total_sales = int(input("What are your total Sales? $"))
# 
# points = 0
# 
# if total_sales >= 0 and total_sales <= 100:
#     points = 10
# elif total_sales >= 101 and total_sales <= 500:
#     points = 20
# elif total_sales >= 501:
#     points = 50
# print(f"You have {points} points")

###This is the Fith exercise from Topic 2###
# a_greater_than_b = False
# b_greater_than_c = False
# c_greater_than_a = False
# 
# a = int(input("Number 1/3 "))
# b = int(input("Number 2/3 "))
# c = int(input("Number 3/3 "))
# 
# if a > b:
#     a_larger_than_b = True
# if b > c:
#     b_greater_than_c = True
# if c > a:
#     c_greater_than_a = True
#     
# if b_greater_than_c == True and c_greater_than_a == True:
#     middle_num = c
# elif
#     
# print(f"{middle_num} is the middle number.")


# a = int(input("Number 1/3 "))
# b = int(input("Number 2/3 "))
# c = int(input("Number 3/3 "))
# error = False
# 
# if b > c and c > a:
#     middle_num = c
# elif (c > a and a > b) or (a > c and b > a):
#     middle_num = a
# elif a > b and b > c:
#     middle_num = b
# else:
#     error = True
#     
# if error == True:
#     print("There is no middle number because two numbers are the same value")
# else:
#     result = print(f"{middle_num} is the middle number.")
###---------------------------------------###
# a = int(input("First Number:  "))
# b = int(input("Second Number: "))
# c = int(input("Third Number: "))
# 
# a_points = 0
# b_points = 0
# c_points = 0
# 
# if a > b:
#     a_points += 1
# if a > c:
#     a_points += 1
# if b > a:
#     b_points += 1
# if b > c:
#     b_points += 1
# if c > a:
#     c_points += 1
# if c > b:
#     c_points += 1
# 
# if a_points == 1:
#     answer = a
# elif b_points == 1:
#     answer = b
# elif c_points == 1:
#     answer = c
# 
# if a_points == b_points or a_points == c_points or c_points == b_points:
#     print("Error: You typed the same number more than once!")
# else:
#     print(f"{answer} is the middle number")
# condition variable for outer loop

###This is the first exercise from Topic 3###
# number = 0
# total = 0
# Number_Track = []
# while number >= 0:
#     number = int(input('Enter a number: '))
#     if number >= 0:
#         total += number
# print(f"The total is {total}")

###This is the second exercise from Topic 3###
# n = int(input("Enter a positive integer n: "))
# total = 0
# 
# for i in range(n + 1):
#     total += 1 / pow(2, i)
#     
# print("The sum of the series is:", total)

###This is the third exercise from Topic 3###
# Laps_Done = int(input('How many laps have you done? '))
# Total_Time = 0
# Fastest_Lap = 0
# Slowest_Lap = 0
# for i in range(1, Laps_Done + 1):
#     Lap_Time = int(input(f'How fast did you lap {i}? '))
#     Total_Time += Lap_Time
#     if i == 1:
#         Fastest_Lap = Lap_Time
#         Slowest_Lap = Lap_Time
#     if Lap_Time > Fastest_Lap:
#         Fastest_Lap = Lap_Time
#     if Lap_Time < Slowest_Lap:
#         Slowest_Lap = Lap_Time
#     
# Average_Time = Total_Time / Laps_Done
# 
# print(f'Your fastest lap time was {Fastest_Lap}')
# print(f'Your slowest lap time was {Slowest_Lap}')
# print(f'Your average lap time was {Average_Time}')

###This is the fith exercise from Topic 3###
# 
# n = int(input('How many rows of "*" do you want? '))
# 
# for i in range(1, n + 1):
#     for y in range(i):
#         print('*', end="")
#     print()

###This is the sixth exercise from Topic 3###
# Num_Years = int(input('Number of years to calculate = '))
# Track_Number = []
# for i in range(1, Num_Years + 1):
#     for j in range(1, 13):
#         Track_Number.append(int(input(f'What was the rainfall in month {j} of year {i}? ')))
# Total = sum(Track_Number)
# Average = Total / len(Track_Number)
# print(f"The total rainfall was {Total} with a monthly average of {Average}")

###This is the first exercise from Topic 5###
# import turtle
# t = turtle
# t.bgcolor('grey')
# t.showturtle()
# t.speed(0)
# t.pensize(10)
# t.begin_fill()
# t.fillcolor('yellow')
# t.circle(200)
# t.end_fill()
# 
# def arc(projection, size, extent):
#     t.pendown()
#     t.right(projection)
#     t.circle(size, extent)
# 
# def move(y, x):
#     t.penup()
#     t.goto(y, x)
# 
# move(-100, 220)
# left_eye = arc(-19, -100, 35)
# 
# move(50, 220)
# right_eye = arc(-30, -100, 35)
# 
# move(-110, 290)
# left_brow = arc(-60, -100, 35)
# 
# move(58, 312)
# right_brow = arc(5, -100, 35)
# 
# move(-76, 110)
# mouth = arc(13, 100, 100)
# 
# t.hideturtle()
# t.done()

###This is the second exercise from Topic 5###
# import turtle
# turtle.bgcolor('black')
# 
# t = turtle.Turtle()
# t.speed(0)
# t.penup()
# t.goto(-100, 200)
# t.pendown()
# t.pensize(25)
# t.color("white")
# 
# t.begin_fill()
# t.fillcolor('red')
# for side in range(8):
#     t.forward(200)
#     t.right(45)
# t.end_fill()
# 
# t.penup()
# t.goto(0, -140)
# t.color("white")
# t.pendown()
# t.write("STOP", align="center", font=("Arial", 120, "bold"))
# 
# t.hideturtle()
# turtle.done()

###This is the third exercise from Topic 5###
# import turtle
# t = turtle
# t.speed(10)
# t.hideturtle()
# 
# def pent(size, colour):
#     for side in range(5):
#         t.pencolor(colour)
#         t.forward(size)
#         t.left(72)
# 
# def pent_group(size, colour):
#     for i in range(8):
#         t.left(45)
#         pent(size, colour)
# 
# pent_group(130, 'orange')
# pent_group(160, 'blue')
# 
# turtle.done()

# ###This is the fourth exercise from Topic 5###
# import turtle
# 
# t = turtle.Turtle()
# t.speed(0)
# t.hideturtle()
# 
# def minute_lines(radius, length, count, thickness):
#     t.penup()
#     t.goto(0, -radius)
#     t.pensize(thickness)
#     for i in range(count):
#         t.forward(radius - length)
#         t.pendown()
#         t.forward(length)
#         t.penup()
#         t.backward(radius)
#         t.right(360 / count)
# 
# def hand(length, angle, thickness, colour):
#     t.pensize(thickness)
#     t.pencolor(colour)
#     t.right(angle)
#     t.pendown()
#     t.forward(length)
#     t.penup()
#     t.backward(length)
#     t.left(angle)
# 
# minute_lines(200, 20, 12, 5)
# minute_lines(200, 10, 60, 2)
# 
# hand(100, 15, 6, 'blue')
# hand(150, 90, 4, 'green')

# turtle.done()
###This is the fith exercise from Topic 5###
# import turtle
# 
# screen = turtle.Screen()
# screen.bgcolor('white')
# t = turtle.Turtle()
# t.speed(0)
# t.penup()
# screen_width = screen.window_width()
# screen_height = screen.window_height()
# 
# def is_out_of_bounds(turtle):
#     x, y = turtle.pos()
#     return x < -screen_width / 2 or x > screen_width / 2 or y < -screen_height / 2 or y > screen_height / 2
# 
# def draw_dvd():
#     t.clear()
#     t.write("DVD", align="center", font=("Arial", 50, "bold"))
# 
# angle = float(input("Enter the initial angle of the turtle's motion: "))
# t.setheading(angle)
# t.hideturtle()
# 
# step_size = 10
# 
# while True:
#     draw_dvd()
#     t.forward(step_size)
#     if is_out_of_bounds(t):
#         t.right(120)
# 
# turtle.done()

###This is the first exercise from Topic 6###
# import random
# 
# def get_input():
#     N = int(input("How many random values do you want to generate? "))
#     return N
# 
# def store_rand_val(storage):
#     storage.append(random.randrange(1, 100))
# 
# def is_even(num):
#     return num % 2 == 0
# 
# def generate_random_numbers(N):
#     rand_nums = []
#     for i in range(N):
#         store_rand_val(rand_nums)
#     return rand_nums
# 
# def sort_odd_even(num_list):
#     even_list = []
#     odd_list = []
#     for num in num_list:
#         if is_even(num):
#             even_list.append(num)
#         else:
#             odd_list.append(num)
#     return odd_list, even_list
# 
# def count_odd_even(odd, even):
#     print(f"odd values = {len(odd)} and even values = {len(even)}")
# 
# def main():
#     N = get_input()
#     rand_nums = generate_random_numbers(N)
#     odd_list, even_list = sort_odd_even(rand_nums)
#     count_odd_even(odd_list, even_list)
# 
# main()
#
###This is the second exercise from Topic 6###
# import math
# 
# def get_input():
#     number = int(input("Type a positive number: "))
#     return number
# 
# def is_positive(num):
#     return num > 0
# 
# def square_root(num):
#     return int(math.sqrt(num))
# 
# def is_prime(num, s_root):
#     if num <= 1:
#         return False
#     if num == 2:
#         return True
#     if num % 2 == 0:
#         return False
#     for i in range(3, s_root + 1, 2):
#         if num % i == 0:
#             return False
#     return True
# 
# def result(prime, num):
#     if prime:
#         print(f"{num} is a prime number")
#     else:
#         print(f"{num} is not a prime number")
# 
# def main():
#     number = get_input()
#     if is_positive(number):
#         root = square_root(number) + 1
#         prime = is_prime(number, root)
#         result(prime, number)
#     else:
#         print("Type a valid number!")
# 
# main()
###This is the forth exercise from Topic 6###

###This is the first exercise from Topic 7###   
"""     
def get_input(prompt, validation_func):
    while True:
        try:
            value = input(prompt)
            if validation_func(value):
                return value
        except ValueError:
            print("Invalid input. Please try again.")

def is_valid_id(value):
    if value.isdigit():
        id_num = int(value)
        if id_num >= 0:
            return True
    elif value.startswith('-') and value[1:].isdigit():
        return True
    return False

def is_valid_name(value):
    return value.isalpha()

def is_valid_mark(value):
    if value.isdigit():
        mark = int(value)
        if 0 <= mark <= 100:
            return True
    return False

def main():
    try:
        with open('student.txt', 'w') as f:
            while True:
                id_num = get_input("Enter student ID (enter a negative number to stop): ", is_valid_id)
                if int(id_num) < 0:
                    break
                
                last_name = get_input("Enter Last Name: ", is_valid_name)
                first_name = get_input("Enter First Name: ", is_valid_name)
                marks = get_input("Enter Marks (0-100): ", is_valid_mark)

                f.write(f"{id_num}\n{last_name}\n{first_name}\n{marks}\n")
        print("Data saved to student.txt")
    except IOError as e:
        print(f"File error: {e}")
    except Exception as e:
        print(f"An unexpected error occurred: {e}")

if __name__ == "__main__":
    main() """

###This is the second exercise from Topic 7###  

def read_marks(file, line_num):
    marks = file.readline(line_num + 3).strip()
    if marks == "":
        return None
    else:
        return int(marks)

def next_student(file, line_num):
    line = file.readline(line_num)
    if not line:
        return False
    else:
        return True

def check_marks(mark):
    if mark > 80:
        return True
    else:
        return False 
    
def display_students(file, line_num):
    print(file.readline(line_num).strip())
    print(file.readline(line_num + 1).strip())
    print(file.readline(line_num + 2).strip())
    print(file.readline(line_num + 3).strip())

def main():
    file = open('student.txt', 'r')
    line_num = 1
    while (next_student(file, line_num)):
        mark = int(read_marks(file, line_num))
        if check_marks(mark):
            display_students(file, line_num)
        line_num += 4

main()
    


