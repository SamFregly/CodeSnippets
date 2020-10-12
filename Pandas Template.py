# assumptions: all returned email columns are named
import sys
import time

import pandas as pd
import numpy as np

start_time = time.time()        # i like to track how long my scripts take
                                # this starts the timer

if not sys.warnoptions:         # surpress some warnings that are annoying to look at
    import warnings

    warnings.simplefilter("ignore")

pd.set_option('display.max_columns', 500)       # play around with max display limits
pd.set_option('display.max_rows', 50)

## everything above this is

csv1 = ['col1A', 'col2A', 'col3A' ,'col4A, Key_columnA]       # put the column names for the first CSV file here for reference
csv2 = ['col1B', 'col2B', 'col3B' ,'col4B, Key_columnB]       # ^^^ for csv 2

ENDING_COLUMN_ORDER = ['KEY_COLUMN', 'col1B', 'col2B', 'col3B' ,'col4B', 'col2A', 'col3A' ,'col4A', ]
            #^^^^^^ This is the final order of your data. You can rearrange the columns in any order.
            #  you can technically pull columns from either csv file. If you dont want a column included in the final version
            # you can not include it in this listing and it will drop off the file. 

csv1_path = # path to csv 1
csv2_path = # path to csv 2

out_file =  # destination of output file


def get_csv1(csv1_path): # i like individual functions to grab each csv file
                         #that way i can do all the clean up in its islated area
    df1 = pd.read_csv(csv1_path, header=0, na_values=[pd.np.nan], dtype={'SSN': str , 'other_col' : int })     # grab csv file from location, the
                                                                # dtype lets you declare certain columns to a type.
                                                                # In my example i turn SSN to str because if its "int" it cuts off leading 0s
                                                                # i would do this for zip codes as well
                                                                # add additional columns with 'column_name from source' : declaration type
                                                                # you will get erros if it cant convert but thats easy to troubleshoot
    df1['DATEO'] = pd.to_datetime(df1['DATE'], format='%Y-%m-%d %H:%M:%S')  # transforming date fields. Probably can do it in the above import, i do it here

    df1.rename({'Col1_bad': 'Col1_good',            # this is for renaming columns. to merge the 2 files they need a column with the same name to match on.
                'Col2_bad': 'col2_good'}, axis='columns', inplace=True) # maybe you want tyo just change some of the names
            # always set inplace = true, 


    df1 = df1.sort_values(by=['Col1', 'Col2'])  ## to sort the data frame by specific columns

    df1 = df1.reset_index(drop=True) #if you rearrange things, drop columns or do things you should reset the index.
                                     # this is the listed row number. It its unique to the row and does not automatically change like it does in excel
                                     # not needed in the grabbing of the file

    return df1 # don't forget to return the data frame (pandas name for an imported csv/excel file) 



def get_csv2(csv2_path): # same thing as 1, gotta do it again.

    df2['New Column'] = 'whatever initalized value you want'    # if you need to create a new column you do it like this
                                                                # aLL values will get set to what you set it as
      for i in range(0, len(df1)):  # This is one method of looping through the data frame. It relies on the index of each row
                                    # this why its importance to use df1.reset_index 
        if df1.loc[i, 'Col1'] == 'Some condition':
            df1.loc[i, 'othercol'] = 'Whatever you want to change it to'
                # insert your logic to manually correct or change things or to set the values of other columns here

    return df2



''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        # END OF FUNCTION CREATION, EVERYTHING BELOW ACTUALLY CALLS COMMANDS AND IS YOUR MAIN() 




First_File = get_csv1(csv1_path)
Second_file = getcsv2(csv2_path)
        
####################################################################################################################################################  
####################################################################################################################################################
df1 = pd.merge(First_file, Second_file, on='Matching column', how='left')  # left join on 'Matching Column'
        # this is your most important line. If both column shares a unqiue key/identifier etc.
        # They will auto match on this column and merge into one super data frame  with all the columns from both but only 1 copy of the matching column

####################################################################################################################################################        
####################################################################################################################################################
        
print(df1) #play with the pd.setoption() command at the top to change how its displayed.
            # after a merge you need to check if it idid it correctly. there are a million things that can go wrong

df1 = df1.drop_duplicates(['Col1','Col2','Col3'])   # you can drop duplicate columns made in the merge.
                                                    # in this example we are checking the set of Col 1 - Col 3
                                                    # if there are multiple rows where the data in these columns is the same
                                                    # Col 1 Row 5 = Col 1 Row 6, Col 2 Row 5 = Col 2 Row 6 and Col 3 Row 5 = Col 3 Row 6
                                                        # then Row 6 will get removed since its seen as a duplicate to row 5.
                                                        # If you dont list any columns it will find duplicates where the entire row is a duplicate
df1 = df1.reset_index(drop=True) # always reset the index after you drop duplicates. Without it your row indexes will look like 1,2,3,7,8,9, etc.
                                 # it resets it to a proper 1,2,3,4,5,6,7,8,9.
                                 # you will have issues looping through it if you dont do it. 
                                        
    
df1 = df1[ENDING_COLUMN_ORDER]      # adjusts the column order to what you defined all the way at the top. 

        
with pd.ExcelWriter(out_file, mode='w') as writer:
    df1.to_excel(writer, sheet_name='Sheet Name', index=False)      # the function is called on df1. It will export an excel file to your desired destination
                                                                    # if you already have the file open you can't export a new version of it. 



print("--- %s seconds ---" % (time.time() - start_time))   # see how long the script took to run. 
 
        
