from nltk.sentiment.vader import SentimentIntensityAnalyzer
from nltk.tokenize import sent_tokenize
import sys

'''
If you use the VADER sentiment analysis tools, please cite:

Hutto, C.J. & Gilbert, E.E. (2014). VADER: A Parsimonious Rule-based Model for
Sentiment Analysis of Social Media Text. Eighth International Conference on
Weblogs and Social Media (ICWSM-14). Ann Arbor, MI, June 2014.
'''

# This is the Vigenere cipher table, also used for the ceasar cipher.
vig = [
    ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
     'x', 'y', 'z'],
    ['b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
     'y', 'z', 'a'],
    ['c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
     'z', 'a', 'b'],
    ['d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
     'a', 'b', 'c'],
    ['e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a',
     'b', 'c', 'd'],
    ['f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b',
     'c', 'd', 'e'],
    ['g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c',
     'd', 'e', 'f'],
    ['h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd',
     'e', 'f', 'g'],
    ['i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e',
     'f', 'g', 'h'],
    ['j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f',
     'g', 'h', 'i'],
    ['k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
     'h', 'i', 'j'],
    ['l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
     'i', 'j', 'k'],
    ['m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
     'j', 'k', 'l'],
    ['n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
     'k', 'l', 'm'],
    ['o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
     'l', 'm', 'n'],
    ['p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
     'm', 'n', 'o'],
    ['q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
     'n', 'o', 'p'],
    ['r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
     'o', 'p', 'q'],
    ['s', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
     'p', 'q', 'r'],
    ['t', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
     'q', 'r', 's'],
    ['u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
     'r', 's', 't'],
    ['v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
     's', 't', 'u'],
    ['w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
     't', 'u', 'v'],
    ['x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
     'u', 'v', 'w'],
    ['y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
     'v', 'w', 'x'],
    ['z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
     'w', 'x', 'y']]

# Basic bitch cipher array
cipher = vig[0]

# Deciphers the text.
def caesarOut(phrase, shift):
    word = ""
    for q in range(len(phrase)):
        # Lower case letters.
        if 97 <= ord(phrase[q]) <= 122:
            word = word + cipher[(cipher.index(phrase[q]) - shift) % 26]
        # Upper case letters.
        elif 65 <= ord(phrase[q]) <= 90:
            # Takes the letter
            letter = phrase[q:q+1]
            # Converts the letter to lowercase char as an int
            letVal = ord(letter) + 32
            # Converts the int back into a char
            letChr = chr(letVal)
            # Converts the character per the cipher rules
            transL = cipher[(cipher.index(letChr) - shift) % 26]
            # Turns it back into an int
            transV = ord(transL) - 32
            # And attaches it back into word as a letter.
            word = word + chr(transV)
        # Non-letter characters.
        else:
            word = word + phrase[q]
    return word

# Deciphers the text.
def vigOut(phrase, keyword):
    kInd = 0
    word = ""
    for q in range(len(phrase)):
        # Lower case letters.
        if 97 <= ord(phrase[q]) <= 122:
            shift = vig[cipher.index(keyword[kInd])]
            lInd = shift.index(phrase[q])
            word = word + cipher[lInd]
            kInd = kInd + 1
            if kInd >= len(keyword):
                kInd = kInd % len(keyword)
        # Upper case letters.
        elif 65 <= ord(phrase[q]) <= 90:
            shift = vig[cipher.index(keyword[kInd])]
            # Takes the letter
            letter = phrase[q:q + 1]
            # Converts the letter to lowercase char as an int
            letVal = ord(letter) + 32
            # Converts the int back into a char
            letChr = chr(letVal)
            # Converts the character per cipher rules
            lInd = shift.index(letChr)
            # Converts it back into an int
            transV = ord(cipher[lInd]) - 32
            # And adds it back into the word as a letter.
            word = word + chr(transV)
            # Computes next keyword index.
            kInd = kInd + 1
            if kInd >= len(keyword):
                kInd = kInd % len(keyword)
        # Non-letter characters.
        else:
            word = word + phrase[q]
            kInd = kInd + 1
            if kInd >= len(keyword):
                kInd = kInd % len(keyword)
    return word

# This is where our emails are contained.
emailFile = open(sys.argv[1], 'r')

# This is where we put them.
emails = []
# And this will be our results.
results = []

# Each line in the email file is an email.
for line in emailFile:
    emails.append(caesarOut(vigOut(line, 'systemic'), 10))

# This is the output.
output = "output"
negScore = 0.0
neuScore = 0.0
posScore = 0.0
comScore = 0.0

# Now that our emails are set up in our list, for every email string in the list of email strings
for i in range(0, len(emails)):

    # We grab each one individually
    inputEmail = emails[i]

    # And turn it into a list of individual sentences to be sent to Lord Vader.
    sent = sent_tokenize(inputEmail, 'english')
    # Total sentences.
    num = len(sent)

    # This is what does all the work for the sentiment analysis.
    sid = SentimentIntensityAnalyzer()

    # And now, for each sentence in our list of sentences
    for sentence in sent:
        # We ask Sid to get the polarity and put it away for later use.
        ss = sid.polarity_scores(sentence)
        # k is category as a string, ss[k] is the percentage that category is represented in the string 'sentence'
        # Three categories exist for this implementation; negative, neutral, and positive.
        for k in ss:
            # This is where we pull out our results and do things to them. Yay things.
            if k == "neg":
                negScore = negScore + ss[k]
            if k == "neu":
                neuScore = neuScore + ss[k]
            if k == "pos":
                posScore = posScore + ss[k]
            if k == "compound":
                comScore = comScore + ss[k]

negFinal = negScore / (len(emails))
neuFinal = neuScore / (len(emails))
posFinal = posScore / (len(emails))
comFinal = comScore / (len(emails))
print("{0} {1} {2} {3}".format(negFinal, neuFinal, posFinal, comFinal))
