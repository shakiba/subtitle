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
      Head(n)          Include only first n subtitle. Use -n to exlude.
      Tail(n)          Include only last n subtitle. Use -n to exlude.
      
      Offset(t)        Move each subtitle t milliseconds.
      Pad(t)           Add t milliseconds before and after each subtitle.
      Pad(tb, ta)      Add tb milliseconds before and ta after each subtitle.
      
      Max(t)           Exlude subtitles which are more than t milliseconds.
      Min(t)           Exlude subtitles which are less than t milliseconds.
      Exclude(tf)      Exlude subtitles which appears after tf milliseconds.
      Exclude(tf, tt)  Exlude subtitles which appears between tf and tt milliseconds.
      
      Sort             Sort by start time.
      
      NoEmpty          Exclude empty subtitle.
      NoExplain        Exclude [EXPLANATORY] subtitles.
      NoDialog         Exclude subtitles which contains a dialog like `ME: Hi|YOU: Bye!` or `- Hi|- Bye`.
      JoinContinue     Join each two subtitles if one ends with and next starts with `...`.