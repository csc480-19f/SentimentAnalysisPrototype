from nltk.sentiment.vader import SentimentIntensityAnalyzer
# Prior to this part, we would make a parser / reader that takes email text and uses the
# tokenizer to separate it into sentences that get filled into an array.
# This is that array.
sent = ["I love you.", "I hate you.", "I both love and hate you.", "Cats are a gift from god."]

# This is what does all the work for the sentiment analysis.
sid = SentimentIntensityAnalyzer()

# This is what loops over the array of sentences. It could be averaged. Maybe even just run a whole
# email through. This is just the prototype, so we could experiment.
for sentence in sent:
    print(sentence)
    # This is a map? (dictionary?) Array? Doesn't matter I guess.
    ss = sid.polarity_scores(sentence)
    # k is category as a string, ss[k] is the percentage that category is represented in the string 'sentence'
    # Three categories exist for this implementation; negative, neutral, and positive.
    for k in ss:
        # So here, we could write them into a .txt file or something for export to the rest of engine.
        print('{0}: {1}, '.format(k, ss[k]), end='')
    print()