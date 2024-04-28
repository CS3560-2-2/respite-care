import os
import pandas as pd

csv_directory = r'src\DefaultData'

output_file = csv_directory + r'\insert_commands.txt'

with open(output_file, 'w') as sql_file:
    # Iterate over each file in the directory
    for filename in os.listdir(csv_directory):
        if filename.endswith('.csv'):
            # Read the CSV file using pandas
            df = pd.read_csv(os.path.join(csv_directory, filename))
            
            # Get the table name from the CSV file name
            table_name = os.path.splitext(filename)[0]
            
            # Write the SQL INSERT command for the current CSV file
            sql_file.write(f"INSERT INTO {table_name} VALUES\n")
            
            # Iterate over each row in the DataFrame
            for idx, row in df.iterrows():
                # Convert each value to a string and wrap it in single quotes
                values = [f"'{str(value)}'" for value in row]
                
                # Join the values with commas and wrap them in parentheses
                row_values = f"({', '.join(values)})"
                
                # Write the row values to the SQL file
                sql_file.write(f"{row_values}")
                # Add a comma and newline if it's not the last row
                if idx < len(df) - 1:
                    sql_file.write(",\n")
                else:
                    sql_file.write(";\n\n")

print(f"SQL script generated successfully: {output_file}")