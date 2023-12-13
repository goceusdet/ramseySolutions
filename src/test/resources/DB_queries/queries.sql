select *
from EMPLOYEES;
select *
from COUNTRIES;
select FIRST_NAME
from EMPLOYEES;
select distinct FIRST_NAME
from EMPLOYEES;
select FIRST_NAME, LAST_NAME
from EMPLOYEES;

-- where
select *
from EMPLOYEES
where FIRST_NAME = 'Peter';
select FIRST_NAME, LAST_NAME, SALARY
from EMPLOYEES
where FIRST_NAME = 'David';
select FIRST_NAME, LAST_NAME, SALARY
from EMPLOYEES
where FIRST_NAME = 'David'
  and LAST_NAME = 'Austin';
select *
from EMPLOYEES
where SALARY > 3000;
select *
from EMPLOYEES
where SALARY > 3000
  and DEPARTMENT_ID = 10;
select *
from EMPLOYEES
where SALARY > 5000
  and SALARY <= 8000;
select *
from EMPLOYEES
where SALARY between 5000 and 8000;
select *
from EMPLOYEES
where EMPLOYEE_ID between 100 and 120;
select *
from EMPLOYEES
where JOB_ID = 'IT_PROG'
   or JOB_ID = 'MK_MAN'
   or JOB_ID = 'SA_REP';
select *
from EMPLOYEES
where JOB_ID in ('IT_PROG', 'MK_MAN', 'SA_REP');
select FIRST_NAME, JOB_ID
from EMPLOYEES
where JOB_ID not in ('IT_PROG', 'MK_MAN', 'SA_REP');
select *
from EMPLOYEES
where MANAGER_ID is null;
select *
from EMPLOYEES
where MANAGER_ID is not null;

-- order by
select *
from EMPLOYEES
order by SALARY asc;
select *
from EMPLOYEES
order by SALARY desc;
select *
from EMPLOYEES
where JOB_ID = 'IT_PROG'
order by salary asc;
select *
from EMPLOYEES
where JOB_ID = 'IT_PROG'
order by salary desc;
select *
from EMPLOYEES
order by FIRST_NAME, LAST_NAME desc;

-- like
select *
from EMPLOYEES
where FIRST_NAME like 'A%';
select *
from EMPLOYEES
where FIRST_NAME like 'A___';
select *
from EMPLOYEES
where FIRST_NAME like '____m';
select *
from EMPLOYEES
where FIRST_NAME like '%m';
select *
from EMPLOYEES
where FIRST_NAME like '_m%';
select *
from EMPLOYEES
where JOB_ID like '%IT%';

-- aggregate functions (they ignore null values)
-- count
select count(*)
from DEPARTMENTS;
select count(*)
from LOCATIONS;
select count(*)
from EMPLOYEES
where DEPARTMENT_ID is null;
select count(DEPARTMENT_ID)
from EMPLOYEES
where DEPARTMENT_ID is null; -- column with null value will be ignored by the aggregate function.
select count(distinct FIRST_NAME)
from EMPLOYEES;
select count(*)
from EMPLOYEES
where JOB_ID in ('IT_PROG', 'SA_REP');
select count(*)
from EMPLOYEES
where SALARY > 6000;
-- max
select max(salary)
from EMPLOYEES;
-- avg
select avg(salary)
from EMPLOYEES;
select round(avg(salary))
from EMPLOYEES;
select round(avg(salary), 1)
from EMPLOYEES;
select round(avg(salary), 2)
from EMPLOYEES;
select round(avg(salary), 3)
from EMPLOYEES;
-- sum
select sum(salary)
from EMPLOYEES;

-- group by
select JOB_ID, avg(SALARY)
from EMPLOYEES
group by JOB_ID;
select JOB_ID, avg(SALARY), min(SALARY), max(SALARY), sum(SALARY), count(*)
from EMPLOYEES
group by JOB_ID;
select DEPARTMENT_ID, sum(SALARY), count(*), max(SALARY), min(SALARY), round(avg(SALARY))
from EMPLOYEES
group by DEPARTMENT_ID;
select DEPARTMENT_ID, sum(SALARY), count(*), max(SALARY), min(SALARY), round(avg(SALARY))
from EMPLOYEES
where DEPARTMENT_ID is not null
group by DEPARTMENT_ID;
select DEPARTMENT_ID, sum(SALARY), count(*), max(SALARY), min(SALARY), round(avg(SALARY))
from EMPLOYEES
where DEPARTMENT_ID is not null
group by DEPARTMENT_ID
order by count(*) desc;
select DEPARTMENT_ID, sum(SALARY), count(*), max(SALARY), min(SALARY), round(avg(SALARY))
from EMPLOYEES
where DEPARTMENT_ID is not null
group by DEPARTMENT_ID
order by count(*) asc;
select DEPARTMENT_ID, sum(SALARY), count(*), max(SALARY), min(SALARY), round(avg(SALARY))
from EMPLOYEES
where DEPARTMENT_ID is not null
group by DEPARTMENT_ID
order by avg(SALARY) desc;

-- having
select DEPARTMENT_ID, sum(SALARY), count(*), max(SALARY), min(SALARY), round(avg(SALARY))
from EMPLOYEES
where DEPARTMENT_ID is not null
group by DEPARTMENT_ID
having avg(SALARY) > 5000
order by avg(SALARY) desc;
--
select DEPARTMENT_ID, count(*) as employees_count
from EMPLOYEES
group by DEPARTMENT_ID
having count(*) > 5;
--
select DEPARTMENT_ID, count(*) as employees_count
from EMPLOYEES
group by DEPARTMENT_ID
having count(*) > 5
order by count(*) desc;
-- checking how many duplicate first names in employees table
select FIRST_NAME, count(*)
from EMPLOYEES
group by FIRST_NAME
having count(*) > 1
order by count(*) desc;

--single row functions
select FIRST_NAME as given_name
from EMPLOYEES;
select FIRST_NAME as "given name"
from EMPLOYEES;
select FIRST_NAME, SALARY * 12 as annual_salary
from EMPLOYEES;

--string functions
select FIRST_NAME || ' ' || LAST_NAME as full_name
from EMPLOYEES;
select concat(EMAIL, '@gmail.com') as full_email
from EMPLOYEES;
select concat('employee ', concat(EMAIL, '@gmail.com')) as full_email
from EMPLOYEES;
-- all uppercase
select upper(EMAIL || '@gmail.com') as full_email
from EMPLOYEES;
select FIRST_NAME || ' ' || upper(LAST_NAME) as full_name
from EMPLOYEES;
-- all lowercase
select lower(EMAIL || '@gmail.com') as full_email
from EMPLOYEES;
select FIRST_NAME || ' ' || lower(LAST_NAME) as full_name
from EMPLOYEES;
-- init cap = first letter is upper case
select initcap(email)
from EMPLOYEES;
-- length
select concat(lower(email), '@gmail.com'), length(email || '@gmail.com') as email_length
from EMPLOYEES;

--sub string
--SUBSTR(columnName, beginingIndex, numberOfChar)
-- if beginning index is 0 it is treated as index 1
-- if beginning index is negative (-1) it is treated as last index
select substr(FIRST_NAME, 0, 1) || '.' || substr(LAST_NAME, 0, 1) || '.' as innitials from EMPLOYEES; -- initials
select substr(FIRST_NAME, 1, 1) || '.' || substr(LAST_NAME, 1, 1) || '.' as innitials from EMPLOYEES; -- initials
select FIRST_NAME, substr(FIRST_NAME, -3, 2) from EMPLOYEES;-- will read from the third letter backwards to the letter after that(including the third letter backward)
select FIRST_NAME, substr(FIRST_NAME, -3) from EMPLOYEES;-- will start reading from third letter counted backward and all the rest of the letters(if beginning is -3 it will read last 3 letters)

--view (creates virtual table that we can read from)
create view EmployeesInfo as
select FIRST_NAME || ' ' || upper(LAST_NAME) || ' makes ' || SALARY as employee_salary, SALARY*12 as annual_salary from EMPLOYEES;
select * from EmployeesInfo;
select employee_salary from EmployeesInfo;
drop view EmployeesInfo;

-- sub-query
select * from EMPLOYEES
where SALARY = (select max(SALARY) from EMPLOYEES);
--
select FIRST_NAME from EMPLOYEES
where SALARY = (select max(SALARY) from EMPLOYEES);
--
select * from EMPLOYEES
where SALARY = (select min(SALARY) from EMPLOYEES);
-- second highest salary
select max(SALARY) from EMPLOYEES
where SALARY < (select max(SALARY) from EMPLOYEES);
-- get all employees with second largest salary
select * from EMPLOYEES
where SALARY = (select max(SALARY) from EMPLOYEES
                where SALARY < (select max(SALARY) from EMPLOYEES));
-- all info who is getting more than avg salary
select round(avg(SALARY)) from EMPLOYEES;
select  * from EMPLOYEES
where SALARY > (select avg(SALARY) from EMPLOYEES);

-- rownum (only works with < and = or both)
select * from EMPLOYEES where ROWNUM <11; -- returns 10 result rows
--bad practice because it cuts the list from line 6 and then it tries to order results within the 5 rows
select * from EMPLOYEES
where rownum < 6
order by SALARY desc;
--correct: first 5 highest salary
select * from (select * from employees order by salary desc)
where rownum < 6;
--select 5th highest salary
select SALARY from EMPLOYEES order by SALARY desc;-- all salary info ordered by desc
select distinct SALARY from EMPLOYEES order by salary desc;-- all distinct salary info ordered by desc
select min(SALARY) from (select distinct SALARY from EMPLOYEES order by salary desc)
where rownum <= 5;-- fifth highest salary.
--
select * from EMPLOYEES
where salary = (select min(SALARY) from (select distinct SALARY from EMPLOYEES order by salary desc)
                where rownum <= 5);-- employee's info that has the fifth highest salary.
--
select * from EMPLOYEES
where salary = (select min(SALARY) from (select distinct SALARY from EMPLOYEES order by salary desc)
                where rownum <= 4);-- employee's info that has the fourth highest salary.
--
select * from EMPLOYEES
where salary = (select max(SALARY) from (select distinct SALARY from EMPLOYEES order by salary)
                where rownum <= 3);-- third lowest salary

