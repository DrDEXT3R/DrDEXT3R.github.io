---
title: "What is CLP?"
date: 2019-03-02
tages: [CLP, Java, constraints]
header:
    image: "/images/CLP/what-is-clp.png"
    teaser: "/images/CLP/what-is-clp.png"
excerpt: "CLP (Constraint Logic Programming) is a form of constraint programming, which is a kind of a declarative approach."
---

## Introduction
Let's start with explaining the abbreviation mentioned above. **CLP (*Constraint Logic Programming*)** is a form of constraint programming, which is a kind of a declarative approach. The main goal of CLP is to **find solutions that will satisfy (all) imposed constraints**.

> <div style="text-align: justify">Constraint programming represents one of the closest approaches computer science has yet made to the Holy Grail of programming: the user states the problem, the computer solves it.</div>
> <div style="text-align: right">Eugene C. Freuder<br/>CONSTRAINTS, April 1997</div>

The general path of the solution:
1. **Observation** - thorough analysis of the problem to be solved;
2. **Model** - determining what conditions / relationships (presented as equations or inequalities) occur in a given problem;
3. **Simulation** - implementation all of these constraints to find a solution.


**Solution** - an assignment (of values to variables), which satisfies constraints.

How can we describe **constraint** in the simplest way? It's a **conglomeration of**: 
* Variables: &nbsp;&nbsp;*A, B, List*
* Function symbols: &nbsp;&nbsp;*+, -, sin*
* Relation symbols: &nbsp;&nbsp;*=, >*

e.g. &emsp;*A < 2*&emsp;&emsp;*3A + 2B = 10*

**Attention!** The order of applying further constraints can change the solution. This is due to the algorithms used.

CLP can be used to solve problems such as: 
* Einstein's Riddle;
* N-Queen problem;
* Knapsack problem.
* Scheduling.

I will show a much simpler example. 


## How to start
Let's face facts, Java is one of the most popular programming languages. For this reason, it also has many libraries. The one that is meant for CLP is called **JaCoP** (<a href="https://osolpro.atlassian.net/wiki/spaces/JACOP/pages/26279944/JaCoP+-+Java+Constraint+Programming+solver" target="_blank">download</a>). You can find the user's guide <a href="http://jacopguide.osolpro.com/guideJaCoP.html" target="_blank">here</a>.

After downloading the library, we **must attach it** to our project and **paste the following lines of code**:
```java
    import org.jacop.core.*;
    import org.jacop.constraints.*;
    import org.jacop.search.*;
```