#  Changelog
All notable changes to this project will be documented in this file

##  [Unreleased]

## [1.12.7] -- 2020-5-12
### Changed
- `game.GameKeyAdapter` no longer relies on javalib

## [1.12.6] -- 2020-5-3
### Added
- `geo.Point.iterPoints(x1,y1,x2,y2)`
- `game.SceneImg.overlayImg(Img img)`

## [1.12.5] -- 2020-4-15
### Changed
- `music.Song.fillTrack()` uses separate channels for instruments
- `music.Song.fillTrack()` no longer creates MIDI events for rests
- `music.Song()` allows integer tempos

## [1.12.4] -- 2020-4-14
### Added
- `geo.PinPoint.distanceTo(PinPoint other)`
- `static geo.PinPoint.adjustForOrigin(PinPoint p, PinPoint o)`
- `geo.PinPoint.round()`

## [1.12.3] -- 2020-4-9
### Added
- `geo.Point.distanceTo(Point other)`
### Changed
- `geo.Point implements Serializable`

## [1.12.2] -- 2020-3-29
### Added
- `img.Img.asPixels()`
- `game.GameSprite`
- `game.SceneImg.placeSprite()`

## [1.12.1] -- 2020-2-12
### Added
- `game.GameUtils`
### Changed
- Moved key/button enums to `GameUtils`

## [1.12] -- 2020-2-12
### Added
- `test.SRList` for stupid classes
- `game` package
- `game.Game` as main abstract class for implementing games
- `game.SceneImg` as Game-specific implementation of `Img`
### Changed
- Made `geo.Fillable` public
- `img.Img` now ignore pixels set out of bounds

## [1.11.2] -- 2020-1-9
### Added
- `test.Tester.assertWithin(Number statement, Number expected, double tolerance)`
- `test.Tester.assertPasses(Object caller, Method m, Object... args)`
- `test.Tester.assertNull(Object statement)`
- `maxFieldDepth` & `doEnumerateFields` settings for `test.TestRunner`
### Changed
- `test.Tester.assertTrue()` renamed as `test.Tester.assertEquals()`
- `test.TestRunner.runTests()` handles exceptions internally

## [1.11.1] -- 2020-1-9
### Changed
- `test.TestRunner` made public
- `test.TestRunner.runTests()` drills down four levels into fields

## [1.11] -- 2020-1-9 (The Testing Update)
### Added
- `test` package
- `test.Tester`: Class used to run assertion tests and examine results
- `test.TestRunner`: Class used to run tests contained within a class

##  [1.10.6]   --   2019-2-4
### Added
- New initializer for `music.Song` from MIDI file
- `music.Song.playAt(int pos)` to play Song at position
- `music.Song.getSheet()` to generate sheet music as a `img.Img`
- `geo.Point.rotateAbout(Point other)` 
### Changed
- `geo.Point.adjustForOrigin(Point in, Point newOrigin)` uses new algorithm
- Primary constructor for `geo.Line` uses new algorithm

## [1.10.5]  --  2018-08-06
### Added
- Ability to set instrument for `music.Song` within text rep
- Various musical notation parsing capabilities for `music.Song`

## [1.10.4.3]
### Fixed
- `music.MInstruments.GRAND_PIANO` now uses proper key

## [1.10.4.2]
### Added
- `music.MInstruments`
- `music.Song(String, MInstruments, boolean)` to make a Song with a specified Instrument

## [1.10.4.1]
### Changed
- `music.Song` constructor parses time signature differently

## [1.10.4]
### Added
- New debug constructor for `music.Song`
- New `Sequence` constructor for `music.Song`
- `music.Song.writeToFile()`
### Changed
- `music.Song.load()` algorithm

## [1.10.3.1]
### Changed
- Constructor for `music.Song`

## *[1.10.3]  --  2018-08-03 (The 3D Update)*
### Added
- `geo.three_dim` subpackage
- `geo.three_dim.Shape3D`
- `geo.three_dim.Point3D`
- `geo.three_dim.Line3D`
### Changed
- Modified IO process for `music.Song.save()` and `music.Song.load()`

## [1.10.2]
### Added
- `geo.ArbitraryShape`
- `geo.Point.equals(Point other)`

## [1.10.1]
### Added
- Ability to play `music.Song`

## **[1.10]  --  2018-04-05 (The Music Update)**
### Added
- `music` package
- `music.Song`
- `music.Notes`
- `music.SlvSound`
- `music.Note`
- `music.Chord`

## [1.9.3]
### Added
- `geo.Shape.strokewidth` property

### Changed
- `geo.Shape.draw()` run process

### Removed
- `geo.Shape.properties` due to stupid implementation

## [1.9.2]
### Added
- `err` package
- `ArgFormatException`

### Moved
- `geo.PointFormatException`; `math.graph.FunctionFormatException` to `err` package

### Changed
- `err.PointFormatException` & `err.FunctionFormatException` now extend `err.ArgFormatException`

## [1.9.1.2]
### Added
- `math.graph.PolarGraph`
- `geo.PolarPoint`
- `geo.Point.toPolar()`

## [1.9.1.1]
### Added
- `math.graph.QuadraticFunc`
- `math.graph.TrigFunc`
- `math.graph.TrigFuncList` enumeration of trigonometric functions

### Changed
- Moved Regular Expressions in Function classes to variables

## [1.9.1]
### Added
- `math.graph` subpackage
- `math.graph.Function`
- `math.graph.LinearFunc`
- `math.graph.FunctionFormatException`
- `math.graph.GraphType`

### Moved
- `math.Quadrant` to `math.graph` subpackage
- `math.Vector` to `math.graph` subpackage

## **[1.9]  --  2018-03-11 (The Algorithmic Update)**
### Added
- `sci` package
- `sci.Physics` class
- `math.Geo` class
- `math.Vector` class
- `geo.PinPoint` class

## [1.8.2]
### Added
- `geo.ImproperFormatException` error
- Constructor for `geo.Point` to build from String

### Changed
- Updated algorithm for generation of `geo.Circle`

## [1.8.1]
### Added
- `math.Num.square(float/double/long)` method

### Changed
- New Mercator projection formula for `map.Mercator`

## [1.8.0.1]
### Added
- `map.Mercator.relocate()` method

## **[1.8]  --  2017-11-28 (The Map Update)**
### Added
- `map` package
- `map.Mercator` class

## [1.7.5]
### Added
- `img.Pixel` class
- `img.Img.drawPixels()` method
- `log.maybeLog()` method
- `math.Quadrant` class

## *[1.7 -> 1.7.4]  --  2017-11-23 (The Movie Updates)* **[YANKED]**

## [1.6.4]
### Added
- `io.IO.runScript()` method

## [1.6.3.2]
### Changed
- `io.IO.readFile()` and `io.IO.readLines()` methods take String arguments

## [1.6.3.1]
### Changed
- `io.IO.readFile()` and `io.IO.readLines()` methods throw `IOException`

## [1.6.3]
### Added
- `io.IO.readFile()` method
- `io.IO.readLines()` method

## [1.6.2]
### Added
- `geo.Fillable` interface
- `geo.Rect.drawFill()` and `geo.Circle.drawFill()` methods

### Fixed
- Constructor algorithm for  `geo.Line` class
- Constructor for empty `img.Img`

## [1.6.1]
### Added
- `io.IO` class

## **[1.6]  --  2017-08-06 (The IO Update)**
### Added
- `io` package

## [1.5.2]
### Added
- `geo.Dot` class

## [1.5.1]
### Added
- `geo.Line` class
- `geo.Rect` class
- `geo.Circle` class

### Removed
- All geometric classes in the `math` class

## **[1.5]  --  2017-08-05 (The Geometry Update)**
### Added
- `geo` package
- `geo.Shape` abstract class
- `geo.Point`

### Removed
- `math.Point`

## [1.4.3]
### Added
- `img.Img.drawPoint()` method

## [1.4.2.1]
### Changed
- Algorithm for generating `math.Circle`

## [1.4.2]
### Added
- `math.Rect` class
- `math.Circle` class

## [1.4.1.1]
### Added
- `math.Function` class

## [1.4.1]
### Added
- `math.Line` class

## **[1.4]  --  2017-07-30 (The Math Update)**
### Added
- `math` package
- `math.Num` class
- `math.Point` class

## [1.3.2]
### Added
- `img.Img.subimage()` method

## [1.3.1]
### Added
- Blank constructor for `img.Img`
- `img.Img.save()` method

## **[1.3]  --  2017-07-21 (The Image Update)**
### Added
- `img` package
- `img.Img` class

## [1.2]
### Added
- `log.Log.printToFile()` method
- `log.Logger` class

## [1.1.1]
### Added
- `log.Err` class

## **[1.1]  --  2017-06-28 (The Logging Update)**
### Added
- `log` package
- `log.Log` class

