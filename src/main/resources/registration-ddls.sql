create table student (
    student_id int not null,
    student_name varchar(100)
)

create table Course (
    course_id int not null,
    course_name varchar(50)
)

create table STUDENT_ENROLLED_COURSES (
    student_id int,
    course_id int,
    marks number(10,2)
)