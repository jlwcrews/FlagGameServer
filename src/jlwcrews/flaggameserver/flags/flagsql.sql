create table flags (flag_id integer primary key not null, country varchar(20), image blob);
create table groups (group_id integer primary key not null, group_name);
create table flag_groups (flag_id int, group_id int, foreign key(flag_id) references flags(flag_id), foreign key(group_id) references groups(group_id));

INSERT INTO flags (country) values('Australia');
INSERT INTO flags (country) values('Austria');
INSERT INTO flags (country) values('Belgium');
INSERT INTO flags (country) values('Bulgaria');
INSERT INTO flags (country) values('Canada');
INSERT INTO flags (country) values('Cyprus');
INSERT INTO flags (country) values('Czech Republic');
INSERT INTO flags (country) values('Denmark');
INSERT INTO flags (country) values('United Kingdom');
INSERT INTO flags (country) values('Estonia');
INSERT INTO flags (country) values('Finland');
INSERT INTO flags (country) values('France');
INSERT INTO flags (country) values('Germany');
INSERT INTO flags (country) values('Greece');
INSERT INTO flags (country) values('Hungary');
INSERT INTO flags (country) values('Iceland');
INSERT INTO flags (country) values('Ireland');
INSERT INTO flags (country) values('Italy');
INSERT INTO flags (country) values('Japan');
INSERT INTO flags (country) values('Latvia');
INSERT INTO flags (country) values('Lithuania');
INSERT INTO flags (country) values('Luxembourg');
INSERT INTO flags (country) values('Malta');
INSERT INTO flags (country) values('Norway');
INSERT INTO flags (country) values('Poland');
INSERT INTO flags (country) values('Portugal');
INSERT INTO flags (country) values('Romania');
INSERT INTO flags (country) values('Russia');
INSERT INTO flags (country) values('Slovakia');
INSERT INTO flags (country) values('Slovenia');
INSERT INTO flags (country) values('Spain');
INSERT INTO flags (country) values('Sweden');
INSERT INTO flags (country) values('The Netherlands');
INSERT INTO flags (country) values('United States');

INSERT INTO groups (group_name) values('European Union');
INSERT INTO groups (group_name) values('Nordics');
INSERT INTO groups (group_name) values('G8');

INSERT INTO flag_groups (flag_id, group_id)
SELECT  flags.flag_id AS flag_id, 1
FROM flags
where flags.country like 'Austria'
or flags.country like 'Belgium'
or flags.country like 'Bulgaria'
or flags.country like 'Cyprus'
or flags.country like 'Czech Republic'
or flags.country like 'Denmark'
or flags.country like 'Estonia'
or flags.country like 'Finland'
or flags.country like 'France'
or flags.country like 'Germany'
or flags.country like 'Greece'
or flags.country like 'Hungary'
or flags.country like 'Ireland'
or flags.country like 'Italy'
or flags.country like 'Latvia'
or flags.country like 'Lithuania'
or flags.country like 'Luxembourg'
or flags.country like 'Malta'
or flags.country like 'The Netherlands'
or flags.country like 'Poland'
or flags.country like 'Portugal'
or flags.country like 'Romania'
or flags.country like 'Slovakia'
or flags.country like 'Slovenia'
or flags.country like 'Spain'
or flags.country like 'Sweden'
or flags.country like 'United Kingdom';

INSERT INTO flag_groups (flag_id, group_id)
SELECT  flags.flag_id AS flag_id, 2
FROM flags
where flags.country like 'Norway'
or flags.country like 'Sweden'
or flags.country like 'Denmark'
or flags.country like 'Iceland'
or flags.country like 'Finland';

INSERT INTO flag_groups (flag_id, group_id)
SELECT  flags.flag_id AS flag_id, 3
FROM flags
where flags.country like 'Canada'
or flags.country like 'France'
or flags.country like 'Germany'
or flags.country like 'Italy'
or flags.country like 'Japan'
or flags.country like 'Russia'
or flags.country like 'United Kingdom'
or flags.country like 'United States';
