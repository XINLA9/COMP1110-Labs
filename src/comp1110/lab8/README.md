# COMP1110 Week 8 Lab

Before you start, you may wish to take a minute to watch the [intro video](https://cs.anu.edu.au/courses/comp1110/labs/mp4/lab8-intro.mp4) that runs through the goals of this lab.

This lab contains material from module [**J14**](https://cs.anu.edu.au/courses/comp1110/lectures/theme/#J14) (Collections) and [**C1**](https://cs.anu.edu.au/courses/comp1110/lectures/theme/#C1) (Recursion).

**Tasks**
1. [Crop Rotation](#1-recursion-sets-and-lists-crop-rotation)

**Additional Exercises**
1. [Hashing](#extension-hashing)
2. [Group Assignment](#2-work-on-your-group-assignment)

## Before the Lab

* Complete Week 8 of your personal journal, commit and push at least 5 minutes prior to the start of your lab.

## Purpose

In this lab you will solve a simple search problem to find all the different orderings of planting crops, given some basic rules.

**Your tutor will mark your engagement during the lab.**

## Part I: Check-In

Check in with **your tutor** together with the other members of your group.    Briefly outline any issues you're having that you'd particularly like help with during the lab (make sure you have your journal open and refer to it when you meet with your tutor).

## Part II:  Your Lab Tasks

### 1. Recursion, Sets, and Lists: Crop Rotation
递归、集合和列表:作物轮作

Complete the missing methods (marked `FIXME`) in the `CropRotation` class.   This question is based on a question from the 2020 S1 final exam.   

To solve it, you need to use sets and lists, and you need to solve a search problem, like the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java) we solved in lectures in unit J14.  
完成CropRotation类中缺少的方法(标记为FIXME)。这个问题是基于2020年S1期末考试的一个问题。

1. **Handle Simple Cases**

    It is a good strategy to identify and deal with simple cases first.   In
     this problem, notice that there are a number of circumstances where you
     should return an empty list.  You should address this simple case first.   
    Add an `if` statement to `getAllRotations()` that checks for the cases where an empty list must be returned, and return the empty `rotations` list.    
    首先识别和处理简单的情况是一个很好的策略。在这个问题中，请注意，在许多情况下都应该返回空列表。您应该首先解决这个简单的情况。  
   向`getalltices()`中添加`if`语句，用于检查必须返回空列表的情况，并返回空的'rotations'列表。  


2. **Implement Switch**

    A search typically involves moving from one possibility to another (for example,
    moving from one die in the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)
    to another).  Doing so involves at least two considerations:
    1. ensuring that the possibility you're considering is not one you've already considered (if you don't do this, your search will never complete because you will continuously revisit cases you've already encountered), and
    2. ensuring that you only consider _legal_ cases.
    
    Ensuring that you don't consider the same case twice was addressed in the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)
    by using an array of booleans called `used[]`, and the code carefully
    kept track of which dice were already considered in the current search (you
    may want to inspect the `findWords()` method in the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)
    to see how this was done).   In this problem you've been given a `List` of 
    `Vegetables` called `used`, which you should use in a similar way, adding
    vegetables to the list when they're being considered, and checking whether
    they're already in the set before you try to use them.
    
    In the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java),
    checking for _legal_ cases was doing by using a set of neighbours, so that
    we knew for each die which were its legal neighbours.   In this case
    you need to implement `canFollow()`, which will allow your code to determine
    whether a particular crop is a legal follower of the current crop.
    
    Use a switch statement to complete the `canFollow()` method, returning the correct value according the the vegetable groups of the `first` and `next` vegetables.  
   搜索通常涉及从一种可能性转移到另一种可能性(例如，从Boggle游戏中的一个骰子转移到另一个)。这样做至少涉及到两个方面的考虑:  
   1. 确保你正在考虑的可能性不是你已经考虑过的(如果你不这样做，你的搜索将永远不会完成，因为你将不断地重温你已经遇到的案例)，以及  
   2. 确保你只考虑法律案件。  
   在[Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)中，通过使用名为used[]的布尔数组来确保您不会两次考虑相同的情况，并且代码仔细地跟踪当前搜索中已经考虑了哪些骰子
   (您可能想要检查[Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)中的`findlords()`方法，以查看这是如何完成的)。
   在这个问题中，你会得到一个名为used的蔬菜列表，你应该以类似的方式使用它，在考虑蔬菜时将其添加到列表中，并在尝试使用它们之前检查它们是否已经在集合中。
   在[Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)中，检查法律案例是通过使用一组邻居来完成的，这样我们就知道每个骰子的合法邻居是什么。在这种情况下，您需要实现`canFollow()`，它将允许您的代码确定特定的作物是否是当前作物的合法追随者。  
   使用switch语句完成`canFollow()`方法，根据`first`和`next`的蔬菜组返回正确的值。

3. **Complete the Search**

    You should notice that the `findWords()` method in the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)
    is not terribly complex once the other parts are in place.   You now 
    need to write the body of `getFixedRotation()`, which will be recursive,
    filling a similar role to `findWords()` method in the [Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java).

    Write the search by completing `getFixedRotation()`, and adding a `for` loop to `getAllRotations()` that calls `getFixedRotation()` for each of the vegetables in `crops`, adding the vegetable to `used` before calling, and removing it from `used` afterwards. 
  
    您应该注意到，一旦其他部分就绪，[Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)中的findWords()方法就不是非常复杂了。
    现在需要编写getFixedRotation()的主体，它将是递归的，填充与[Boggle game](https://gitlab.cecs.anu.edu.au/comp1110/comp-1110-lectures-s-2-2022/-/blob/master/src/comp1110/lectures/J14/Boggle.java)中的findWords()方法类似的角色。
    通过完成getFixedRotation()来编写搜索，并向getallrotation()添加一个for循环，该循环为作物中的每一种蔬菜调用getFixedRotation()，在调用之前将蔬菜添加到`used`中，然后从`used`中删除它。  

## Part III: Additional Exercises 

Remember that these are additional exercises for your own practice. They are not compulsory, but your tutor will be happy to provide assistance and feedback during the lab.

### 1. Hashing

**Create a hash program**

Using the lecture code from C03 as a template:

* Read the data from `assets\us_postal_codes.txt`. Each line contains a postal code,town name, state name, state code,county name, latitude and longitude.


Then:
Imagine you are in charge of the entire US postal service, and you have only 50 delivery officers to cover the entire country (40933 zip codes).
You want to give each person a roughly equal amount of work (imagine each post code has the same amount of mail).

Create a hash function that will:
1. Split the work as evenly as possible (imagine that each post code has the same amount of mail)
2. Minimise the travel each person does as much as possible. Imagine that each person starts at any one of their zip codes, and avoid them having to travel across the country.

You can test 1. by running the program and checking the histogram of results to see if the hash function is even.


How many ways can you do this?

### 2. Work on your group assignment

Use any spare time to work on your group assignment.




