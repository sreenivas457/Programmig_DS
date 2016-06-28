This project has three folders, Source code (src), unit tests (test) and input folder. As names suggest, these folders contain source code, unit test cases and input files.
To run unit tests, Junit library should be included (If you are using latest Junit, check to see if you need hamcrest library as well).
##Source
The source code has following classes. 
###Response
This class stores response and corresponding description. Response is mapped to temperature type. To store this mapping, We use Map as these are <Key, Value> pairs and HashMap, so that lookup is O(1). Response is taken as object to ensure encapsulation of map and description. 

###CommandBuilder
CommandBuilder class maps command number to the corresponding response object.  Each command number has a response object associated to it. Again, we use a HashMap, for mapping and lookup.

**The design of Reponse and CommandBuilder classes using HashMap datastructure is chosen in such a way that it is easily extendible for more commands and more temperature types.

###ReadInput
ReadInput class reads input from a file and builds commands from it. This generates a CommandBuilder object from input file. Input file is expected to be in a specific format.

**To avoid hardcoding and ensure easy extendibility, input is read from a file, To add a new temperature type or command we just need to add it to the input file. 

###Rules
Rules class contains all the business login for our system. It applies rules on input by cross checking them with command builder and generating output based on given business rules. applyRules method which is the core component of our ruleengine starts with a check for initial command of RemovingPJs. It then process input commands one at a time by storing previously executed commands in a set ( so that no clothing is duplicated). Since no duplicates, Set data structure is chosen. HashSet to ensure O(1) lookup. The same set is used to see if all the mandatory clothing is worn before leaving the house.
###RunProgram
RunProgram has main method, which provides a way to interact via console. It expects the following: a String Temperature type (HOT/COLD) followed by a space and sequence of command provided as integers separated by commas.



##Unit Test Classes
Test classes are provided for Response, CommandBuilder and Rules Classes. ResponseTest and CommandBuilderTest contain unit test cases to test corresponding classes. 
RulesTest Class contains basic tests covering a range of possible commands covering business rules. Each test is named in such a way that test case name conveys the actual test it is performing. With unit test cases, it becomes easy to make changes without breaking existing logic.

#Run Executable

Copy ds_tempadvise_data.csv from the input folder and place it in the same folder as jar.

To run executable jar, 

java -jar Programming_DS.jar RunProgram
