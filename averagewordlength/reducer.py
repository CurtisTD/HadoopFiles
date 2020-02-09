#!/usr/bin/env python
"""reducer.py"""

from operator import itemgetter
import sys

curr_word = None
word = None
curr_count = 0

#stdin text
for line in sys.stdin:
    #remove whitespace
    line = line.strip()

    word, count = line.split('\t', 1)

    try:
        count = int(count)
    except ValueError:
	print 'Error on value\n'
        continue

    if curr_word == word:
        curr_count += count
    else:
        if curr_word:
            print '%s\t%s' % (curr_word, curr_count)
        curr_count = count
        curr_word = word

if curr_word == word:
    print '%s\t%s' % (curr_word, curr_count)
