#   If you are moving your data to a new server, or you have removed the old
# database completely you can restore it using the code below. This will only
# work if the database does not already exist:
#   mysql -u <user_name> -p<password> <database_name> < <data_file_name.sql>

# If your database already exists and you are just restoring it, try this
# line instead:
#   mysqlimport -u <user_name> -p<password> <database_name> <file_name.sql>

mysql -u quicksilver -p<password> quicksilver < backup.sql
