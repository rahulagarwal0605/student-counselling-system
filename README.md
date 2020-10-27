# **Project Report**

## **Student Counselling System**

## Classes

1. **Student**

    *Properties:*
- rollNum: int
- name: String
- dob: String
- rank: int
- state: String
- stateId: int
- gender: char
- category: String

    *Methods:*
- addStudent()
- removeStudent()
- showStudent()
- addPreference()
- changePreference()
- getResult()
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
- showAllBranch();

3. **Institutes**

    *Properties:*
- instituteID: int
- instituteName: String
- city: String
- state: String

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

1. Student (sid, name, dob, roll_num, rank, state, gender, category)
- PK: sid

2. Choice (cid, sid, bid)
- PK: cid
- FK: sid references Student(sid)
- FK: bid references Branch(bid)

3. Branch (bid, branch_name, duration, degree)
- PK: bid

4. State (id, state)
- PK: id

5. Institute (iid, institute_name, city, state_id)
- PK: iid
- FK: state_id references State(id)

6. GovernmentInstitutes (iid, bid, quota, seat_pool, category, or, cr, total_seats, alloated_seats, vacant_seats)
- PK: iid, bid, quota, seat_pool, category
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)

7. PrivateInstitutes (iid, bid, or, cr, total_seats, alloated_seats, vacant_seats)
- PK: iid, bid
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)

8. AdmittedStudents (aid, roll_num, iid, bid)
- PK: aid, roll_num
- FK: roll_num references Student(roll_num)
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)