# **Student Counselling System**

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
- createStudent()
- addStudent()
- addPreference()
- showPreference()
- updatePreference()
- removePreference()
- getResult()
- getAdmission()
- changeAdmission()
- removeStudent()
- showStudent()

2. **Branch**

    *Properties:*
- branchID: int
- branchName: String
- duration: int
- degree: String

    *Methods:*
- createBranch()
- addBranch()
- showBranch()
- updateBranch()
- removeBranch()

3. **Institutes**

    *Properties:*
- instituteID: int
- instituteName: String
- city: String
- state: String

    *Methods:*
- createInstitute()
- addInstitute()
- showInstitute()
- updateInstitute()
- removeInstitute()

4. **GovernmentInstitutes extends Institutes**

    *Properties:*
- branch: Branch
- quota: String
- seat_pool: String
- category: String
- openingRank: int
- closingRank: int

    *Methods:*
- addInstitute()
- removeInstitute()
- showInstitute()

5. **PrivateInstitutes extends Institutes**

    *Properties:*
- branch: Branch
- openingRank: int
- closingRank: int

    *Methods:*
- addInstitute()
- removeInstitute()
- showInstitute()

6. **Main**

    *Methods:*
- initDB()
- createDB()
- conDB()
- main()

## **Databases**

1. Student (roll_num, name, dob, state_id, gender, category, exam_rank)
- PK: roll_num
- FK: state_id references State(id)

2. Choice (cid, roll_num, bid)
- PK: cid
- FK: roll_num references Student(roll_num)
- FK: bid references Branch(bid)

3. Branch (bid, branch_name, duration, degree)
- PK: bid

4. State (id, state)
- PK: id

5. Institute (iid, institute_name, city, state_id)
- PK: iid
- FK: state_id references State(id)

6. Josaa (id, iid, bid, quota, seat_pool, gen_or, gen_cr, gen_ews_or, gen_ews_cr, obc_or, obc_cr, sc_or, sc_cr, st_or, st_cr)
- PK: id
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)

7. Private (id, iid, bid, or, cr)
- PK: id
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)

8. AdmittedStudents (roll_num, iid, bid)
- PK: roll_num
- FK: roll_num references Student(roll_num)
- FK: iid references Institute(iid)
- FK: bid references Branch(bid)