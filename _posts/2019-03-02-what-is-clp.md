---
title: "What is CLP?"
date: 2019-03-02
tages: [CLP, Java, constraints]
header:
    image: "/images/CLP/what-is-clp.png"
    teaser: /images/CLP/what-is-clp.png
    og_image: /images/CLP/what-is-clp.png
excerpt: "CLP (Constraint Logic Programming) is a form of constraint programming, which is a kind of a declarative approach."
mathjax: "true"
---

## Introduction
Let's start with explaining the abbreviation mentioned above. **CLP (*Constraint Logic Programming*)** is a form of constraint programming, which is a kind of a declarative approach. The main goal of CLP is to **find solutions that will satisfy (all) imposed constraints**.

> <div style="text-align: justify">Constraint programming represents one of the closest approaches computer science has yet made to the Holy Grail of programming: the user states the problem, the computer solves it.</div>
> <div style="text-align: right">Eugene C. Freuder<br/>CONSTRAINTS, April 1997</div>

The general path of the solution:
1. *Observation* - thorough analysis of the problem to be solved;
2. *Model* - determining what conditions / relationships (presented as equations or inequalities) occur in a given problem;
3. *Simulation* - implementation all of these constraints to find a solution.
<br/><br/>

**Solution** - an assignment (of values to variables), which satisfies constraints.

How can we describe **constraint** in the simplest way? It's a **conglomeration of**: 
* Variables: &nbsp;&nbsp; $$ A, B, List $$
* Function symbols: &nbsp;&nbsp; $$ +, -, sin $$
* Relation symbols: &nbsp;&nbsp; $$ =, < $$

$$ e.g. $$ &emsp; $$ A < 2 $$ &emsp;&emsp; $$ 3A + 2B = 10 $$

<span style="text-decoration:underline">Attention!</span> The order of applying further constraints can change the solution. This is due to the algorithms used. <br/>

## Simple example 

We have two sets: <br/>
$$ A = { 4, 5, 6, 7, 8, 9, 10 } $$<br/>
$$ B = { 3, 4, 5, 6 } $$<br/><br/>
We want to find a pair of numbers that will satisfy the following constraints: <br/>
$$ A>B $$<br/>
$$ A+B=9 $$

After a moment of reflection, we come to the conclusion that the solution may be: <br/>
$$ A = 5, B = 4 $$ <br/>
$$ A = 6, B = 3 $$

As we can see, it was not difficult, but let us note that the example was trivial. You can try to imagine more sets and relationships between them. <br/>

CLP can be used to solve problems such as: 
* Einstein's Riddle;
* N-Queen problem;
* Knapsack problem;
* Scheduling.

## How to start
Let's face facts, Java is one of the most popular programming languages. For this reason, it also has many libraries. The one that is meant for CLP is called **JaCoP** (<a href="https://osolpro.atlassian.net/wiki/spaces/JACOP/pages/24248331/JaCoP+Download" target="_blank">download</a>). You can find the user's guide <a href="http://jacopguide.osolpro.com/guideJaCoP.html" target="_blank">here</a>.

After downloading the library, we **must attach it** to our project and **paste the following lines of code**:
```java
import org.jacop.core.*;
import org.jacop.constraints.*;
import org.jacop.search.*;
```

Now we can start writing our program. We will need:
1. Some place for storing CLP variables;
2. An array for storing all sets;
3. Defined constraints - we will use the special *impose()* function for this purpose.

```java
// Store for CLP variables
Store store = new Store();

// Array of sets
IntVar[] vars = new IntVar[2];

// Defining sets
vars[0] = new IntVar(store, "A", 4, 10);
vars[1] = new IntVar(store, "B", 3, 6);

// Imposing constraints
store.impose( new XgtY(vars[0], vars[1]) );
store.impose( new XplusYeqC(vars[0], vars[1], 9) );
```

Note that the representation of our set $$ A $$ will be **vars[0]**, and the set $$ B $$ **vars[1]**. We will use this knowledge when imposing constraints. <br/>
***XgtY*** means $$ X>Y $$ (gt - greater than) and ***XplusYeqC*** means $$ X+Y=Const $$ (eq - equal). You can find the whole list in the <a href="http://jacopguide.osolpro.com/guideJaCoP.html" target="_blank">user's guide</a>.
<br/>
<div style="text-align: center;">
    <img alt="result" src="/images/CLP/constraints.png">
</div>
<br/>
Now we can **look for a solution**. We do this by adding the following lines to our program:
```java
Search label = new DepthFirstSearch(); 
SelectChoicePoint select = new InputOrderSelect(store, vars, new IndomainMin());
label.labeling(store, select); 
```
Without going into details, we define here how we want to find a solution. As we know, there are 2 possible solutions in our example. However, the program result returns only one. 

<div style="text-align: center;">
    <img alt="result" src="/images/CLP/result.png">
</div>

To see the second solution, we can change "*new IndomainMin()*" into "*new IndomainMax()*". There is also a way to **display all possible solutions**.
```java
Search label = new DepthFirstSearch(); 
label.getSolutionListener().searchAll(true); 
SelectChoicePoint select = new InputOrderSelect(store, vars, new IndomainMin());
label.getSolutionListener().recordSolutions(true); 
label.labeling(store, select);
label.printAllSolutions();
```
<div style="text-align: center;">
    <img alt="result" src="/images/CLP/result2.png">
</div>

## Summary
In conclusion, I hope that I have explained well what CLP is and I showed it is not so difficult. More advanced examples show its strength and become practically unfeasible for humans. In case of any problems, you can post comments below. In turn, the entire program can be found <a href="https://www83.zippyshare.com/v/aDY7fH3L/file.html" target="_blank">here</a>.