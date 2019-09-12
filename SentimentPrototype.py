from nltk.sentiment.vader import SentimentIntensityAnalyzer
from nltk.tokenize import sent_tokenize
# import time
import sys

# time.time() returns a floating point value, where 1.0 is one second.
# begin = time.time()

# This is where our emails are contained.
emailFile = open(sys.argv[1], 'r')

# This is where we put them.
emails = []
# And this will be our results.
results = []

# Each line in the email file is an email.
for line in emailFile:
    emails.append(line)

# Now that our emails are set up in our list, for every email string in the list of email strings
for i in range(0, len(emails)):

    # We grab each one individually
    inputEmail = emails[i]

    # And turn it into a list of individual sentences to be sent to Lord Vader.
    sent = sent_tokenize(inputEmail, 'english')

    # This is what does all the work for the sentiment analysis.
    sid = SentimentIntensityAnalyzer()

# TODO Find out if we want only the compound score, or if we want to send over all the scores.
    total = 0.0
    compT = 0.0

    # And now, for each sentence in our list of sentences
    for sentence in sent:
        # We ask Sid to get the polarity and put it away for later use.
        ss = sid.polarity_scores(sentence)
        # k is category as a string, ss[k] is the percentage that category is represented in the string 'sentence'
        # Three categories exist for this implementation; negative, neutral, and positive.
        for k in ss:
            # TODO This would change depending on if we want to use things other than the compound score.
            # TODO This could also write to a file if we want to do something more fancy than a general average.
            # TODO The emails will be sentiment analyzed in the order they get written, so. It's one to one.
            # This is where we pull out our results and do things to them. Yay things.
            if k == "compound":
                total = total + 1
                compT = compT + ss[k]

# TODO This print will be what delivers the final result. Send a string that can be parsed for doubles' values.
finalT = compT / total
print(finalT)

'''
# See above.
end = time.time()
# Total time elapsed in seconds.
total = end - begin
print(total*3)
'''
