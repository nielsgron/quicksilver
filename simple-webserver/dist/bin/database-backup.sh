#!/bin/bash
SELF=$(cd $(dirname $0); pwd -P)
if [ ! -e $SELF/../backup ] ; then
    mkdir $SELF/../backup
fi
BACKUPDIR=$SELF/../backup/`date +"%y-%m-%d_%H-%M-%S"`
mkdir $BACKUPDIR
mysqldump --user=quicksilver --password=<mysql_user_password> quicksilver > $BACKUPDIR/backup.sql
gzip $BACKUPDIR/backup.sql
scp $BACKUPDIR/backup.sql.gz quicksilver@<remote_backup_ip_address>:/home/quicksilver/backup/backup.sql.gz
