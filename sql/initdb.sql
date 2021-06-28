DROP SCHEMA IF EXISTS clups;
DROP USER IF EXISTS 'clupsDevUser'@'%';

CREATE SCHEMA clups; -- Creates the new database
CREATE USER 'clupsDevUser'@'%' identified by 'ClupsDevUserPassword'; -- Creates new database
GRANT all ON clups.* to 'clupsDevUser'@'%'; -- Gives all priviliges to the new user on the newly created database