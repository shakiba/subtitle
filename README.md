subtitle
========

**Experimental** Java API and command line subtitle editing utility.

    ARGS:
      <Reader>  <Filter>;<Filter>;...  <Writer>  <input/file>|"stdin"  <output/file>|"stdout"
    
    READERS:
      SubRip           SubRip (.srt) format reader.
      MicroDVD(r)      MicroDVD (.sub) format reader, r is frame rate.
    
    WRITERS:
      SubRip           SubRip (.srt) format writer.
      MicroDVD(r)      MicroDVD (.sub) format writer, r is frame rate.
    
    FILTERS:
      Head(n)          Only first n subtitle.
      Tail(n)          Only last n subtitle.
      
      Offset(t)        Move each subtitle t milli seconds.
      Pad(t)           Adds t milli seconds to begining and end of each subtitle.
      Pad(ts, te)      Adds ts milli seconds to start and and te to end of each subtitle.
      
      Max(t)           Exlude subtitles which are more than t milli seconds.
      Min(t)           Exlude subtitles which are less than t milli seconds.
      Exclude(s, e)    Exlude subtitles which appears between ts and te milli seconds.
      
      Sort             Sort by start time.
      
      NoEmpty          Exclude empty subtitle.
      NoExplain        Exclude [EXPLANATORY] subtitles.
      NoDialog         Exclude subtitles which contains a dialog like `ME: Hi|YOU: Bye!` or `- Hi|- Bye`.
      JoinContinue     Join each two subtitles if one ends with and next starts with `...`.

