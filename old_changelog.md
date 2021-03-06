* *1.1*: log.Log
  * *1.1.3*: log.Err

* *1.2*: log.Log.printToFile(); log.Logger

* *1.3*: img.Img & img
  * *1.3.1*: Constructor #2 & save func for img.Img
  * *1.3.2*: subimage func for img.Img

* *1.4*: math **+** math.Num
  * *1.4.1*: math.Line
    * *1.4.1.1*: math.Fraction
  * *1.4.2*: math.Rect:Circle
    * *1.4.2.1*: Adjustments to math.Circle (*1.4.3-b001*)
  * *1.4.3*: drawPoint func for img.Img

* *1.5*: geo & geo.Shape
  * *1.5.1*: geo.Line; geo.Rect; geo.Circle
  * *1.5.2*: geo.Dot

* *1.6*: io
  * *1.6.1*: io.IO
  * *1.6.2*: geo.Fillable; fix Line init; fix empty Img init; drawFill for Rect & Circle
  * *1.6.3*: io.IO.getPath:readFile:readLines
    * *1.6.3.1*: io.IO.readFile:readLines throws IOException (*1.6.4-b001*)
    * *1.6.3.2*: io.IO.readFile:readLines take String (*1.6.4-b002*)
  * *1.6.4*: io.IO.runScript

* *1.7*: img.Scene&Movie
    * *1.7.0.1*: geo.Shape.setProp()
  * *1.7.1*: setProp() overrides in Shape subclasses,remake() for Shape subclasses,Shape subclasses get properties
  * *1.7.2*: img.Scene.next():getImg():Scene()
  * *1.7.3*: img.Movie.Movie():init()
  * *1.7.4*: img.ShapeMod:NoSuchShapeException;
          img.Movie.addShape:moveShape:moveShapeTo:changeShapeColor:editShape:commitChanges;
          img.Scene.next2; geo.NoSuchPropertyException; geo.Shape.props()
  * *1.7.5*: **+** img.Pixel; **+** img.Img.drawPixels(); **+** log.Log.maybeLog(); **+** math.Quadrant

* *1.8*: map
  * *1.8.0*: map.Mercator; map.Mercator.map()
    * *1.8.0.1*: map.Mercator.relocate()
  * *1.8.1*: New Mercator formula; **+** math.num.square(float/double/long)
  * *1.8.2*: **+** geo.ImproperFormatException; **+** geo.Point.Point(String); small fixes in geo.Circle

* *1.9*: Algorithms
  * *1.9.0*: sci; sci.Physics; math.Geo; math.Vector; geo.PinPoint
  * *1.9.1*: math.graph; math.graph.Function; math.graph.LinearFunc; math.graph.FunctionFormatException;
          math.graph.GraphType; math.Quadrant -> math.graph.Quadrant; math.Vector -> math.graph.Vector
    * *1.9.1.1*: **+** math.graph.QuadraticFunc; **+** math.graph.TrigFunc; **+** math.graph.TrigFuncList; Encapsulate RegExps
    * *1.9.1.2*: **+** math.graph.PolarGraph; **+** geo.PolarPoint; **+** geo.Point.toPolar()
  * *1.9.2*: **+** err; **+** err.ArgFormatException; geo.PointFormatException -> err.&; math.graph.FunctionFormatException ->
          err.&; **$** err.FunctionFormatException extds err.ArgFormatException; **$** geo.PointFormatException extds
          err.ArgFormatException
  * *1.9.3*: **-** geo.Shape.properties; **+** geo.Shape.strokewidth; **$** geo.Shape.draw()

* *1.10*: Music and 3D Graphics
  * 1.10.0: music; music.Song; music.Notes; music.SlvSound; music.Note; music.Chord
  * 1.10.1: music.Song.play()
  * 1.10.2: **+** geo.Point.equals(); **+** geo.ArbitraryShape
  * 1.10.3: **+** geo.three_dim; **+** geo.three_dim.Shape3D; **+** geo.three_dim.Point3D; **+** geo.three_dim.Line3D;
          **$** music.Song.save(); **+** music.Song.load()
    * 1.10.3.1: **$** music.Song.Song()
  * 1.10.4: **$** music.Song.Song(String, boolean); **+** music.Song.Song(Sequence); **+** music.Song.fillTrack();
          **+** music.Song.getFunction(); **$** music.Song.writeToFile(); **$** music.Song.load()
    * 1.10.4.1: **$** music.Song() now better incorporates time signature
    * 1.10.4.2: **+** music.Song(String, MInstruments, boolean); **+** music.MInstruments (*1.10.5-b001*)
    * 1.10.4.3: **$** music.MInstruments.GRAND_PIANO (*1.10.5-b002*)
    * *1.10.5-b002 -> b010*: **$** Further advancements in music.Song (Including trill and tremolo)
  * 1.10.5: Instrument setting for music.Song
  * 1.10.6: **+** Song.init(File midiEventFile); **+** music.Song.playAt(int pos); **+** music.Song.getSheet();
            **+** geo.Point.rotateAbout(Point); **$** geo.Point.adjustForOrigin(Point,Point); **$** geo.Line.init()
