# **Project Report**

## **Student Counselling System**

**Group(6) Members:**

- 19ucs264 Rahul Agarwal
- 19ucs181 Vaibhav Kagathara
- 19ucs184 Manish Porwal
- 19ucs211 Akshat Chhaparwal

## Classes

1. **Student**

    *Properties:*
- name: String
- dob: Date
- rollNum: int
- rank: int
- quota: String
- gender: char
- category: String
- Preferences: Branch[2]

    *Methods:*
- addStudent()
- removeStudent()
- showStudent()
- getResult()
- changeRank()
- getAdmission()

2. **Branch**

    *Properties:*
- branchID: int
- branchName: String
- duration: int
- degree: String

    *Methods:*
- addBranch()
- removeBranch()
- showBranch()

3. **Institutes**

    *Properties:*
- instituteID: int
- instituteName: String
- city: String

    *Methods:*
- addInstitute()
- removeInstitute()
- showInstitute()

4. **GovernmentInstitutes extends Institutes**

    *Methods:*
- addInstitute()
- removeInstitute()
- showInstitute()

5. **PrivateInstitutes extends Institutes**

    *Methods:*
- addInstitute()
- removeInstitute()
- showInstitute()

6. **Main**

    *Methods:*
- 

## **Databases**

1. Student (name, dob, roll_num, rank, quota, gender, category)
- PK: roll_num

2. Choices (cid, roll_num, rank, prefrence_1, prefrence_2,)
- PK: cid, roll_num
- FK: roll_num references Student(roll_num)

3. Branch (bid, branch_name, duration, degree)
- PK: bid

4. Institute (iid, institute_name, city)
- PK: iid

5. GovernmentInstitutes (iid, bid, quota, seat_pool, category, or, cr, total_seats, alloated_seats, vacant_seats)
- PK: iid, bid, quota, seat_pool, category
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)

6. PrivateInstitutes (iid, bid, or, cr, total_seats, alloated_seats, vacant_seats)
- PK: iid, bid
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)

7. AdmittedStudents (aid, roll_num, iid, bid)
- PK: aid, roll_num
- FK: roll_num references Student(roll_num)
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)


