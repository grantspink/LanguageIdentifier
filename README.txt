-------------------------------
--Language Identifier README--
-------------------------------

--------General Overview-------
Detects what language a piece of text is written in.
Can detect the languages of multiple text files at once. 

----------How it Works---------
1. Constructs a database of language samples from Wikipedia articles.
2. Accepts text file(s) for detection.
3. Returns the most probable language for each text file given the language samples used for comparison.

Language analysis utilizes the monographs, digraphs, and trigraphs that appear in the text files and language samples.

-------Modes of Operation-------
1. Comparing one text file to multiple language samples.
The language sample which most closely matches the text file will be returned.

2. Comparing multiple text files to a single language sample.
The text file which most closely matches the language sample will be returned.

----Understanding the Output----
The numbers printed out represent the relative probabilities of a language match, calculated using either a monograph, digraph, or trigraph analysis.
The final language match probability is then calculated using a 10-20-70 weighting of these probabilities.

- A monograph is a single letter that may appear in a word.
- A digraph is a two-letter consecutive string inside a word.
- A trigraph is a three-letter consecutive string inside a word.
Ex: the word "probability has:
Monographs: [p,r,o,b,a,b,i,l,i,t,y]
Digraphs:   [pr,ro,ob,ba,ab,bi,il,li,it,ty]
Trigraphs:  [pro,rob,oba,bab,abi,bil,ili,lit,ity]

The analysis consists of finding the frequency of the graphemes in the language(s), and the frequency of the graphemes in the input text(s).
Using these, we calculate the probability of a random assortment of graphemes from the language giving exactly the assortment of graphemes in the input text.
(Think monkeys on a typewriter writing Hamlet).
Finally, using these absolute probabilities, we calculate the relative probabilities of the users' selections by dividing by an appropriate factor.
Most of the time, the relative probabilities work out such that one language is 100% likely to be a match, as compared to any other language.