package silverlib.music;

public enum MIntruments {
    GRAND_PIANO(0, "GRAND_PIANO"),
    CHURCH_ORGAN(19, "CHURCH_ORGAN"),
    HARMONICA(22, "HARMONICA"),
    ACOUSTIC_GUITAR(24, "ACOUSTIC_GUITAR"),
    ELECTRIC_GUITAR(26, "ELECTRIC_GUITAR"),
    BASS(32, "BASS"),
    SYNTH_BASS(38, "SYNTH_BASS"),
    VIOLIN(40, "VIOLIN"),
    CELLO(42, "CELLO"),
    HARP(46, "HARP"),
    STRING_ENSEMBLE(48, "STRING_ENSEMBLE"),
    CHOIR(52, "CHOIR"),
    VOICE(53, "VOICE"),
    SYNTH_VOICE(54, "SYNTH_VOICE"),
    TRUMPET(56, "TRUMPET"),
    TROMBONE(57, "TROMBONE"),
    TUBA(58, "TUBA"),
    FRENCH_HORN(60, "FRENCH_HORN"),
    TENOR_SAX(66, "TENOR_SAX"),
    OBOE(68, "OBOE"),
    CLARINET(71, "CLARINET"),
    PICCOLO(72, "PICCOLO"),
    FLUTE(73, "FLUTE"),
    SITAR(104, "SITAR"),
    STEEL_DRUM(114, "STEEL_DRUM");

    private final int id;
    private final String name;

    MIntruments(int i, String n) {
        id = i;
        name = n;
    }

    public int getId(){
        return id;
    }

    public static MIntruments match(String nm){
        for (MIntruments mi:MIntruments.values()){
            if (mi.name == nm){
                return mi;
            }
        }

        return GRAND_PIANO;
    }
}

/*
Instrument:Acoustic Grand Piano bank #0preset #0
Instrument:Bright Acoustic Pian bank #0preset #1
Instrument:Electric Grand Piano bank #0preset #2
Instrument:Honky-tonk Piano bank #0preset #3
Instrument:Electric Piano 1bank #0preset #4
Instrument:Electric Piano 2bank #0preset #5
Instrument:Harpsichord bank #0preset #6
Instrument:Clavi bank #0preset #7
Instrument:Celesta bank #0preset #8
Instrument:Glockenspiel bank #0preset #9
Instrument:Music Box bank #0preset #10
Instrument:Vibraphone bank #0preset #11
Instrument:Marimba bank #0preset #12
Instrument:Xylophone bank #0preset #13
Instrument:Tubular Bells bank #0preset #14
Instrument:Dulcimer bank #0preset #15
Instrument:Drawbar Organ bank #0preset #16
Instrument:Percussive Organ bank #0preset #17
Instrument:Rock Organ bank #0preset #18
Instrument:Church Organ bank #0preset #19
Instrument:Reed Organ bank #0preset #20
Instrument:Accordion bank #0preset #21
Instrument:Harmonica bank #0preset #22
Instrument:Tango Accordion bank #0preset #23
Instrument:Acoustic Guitar(nyl bank #0preset #24
Instrument:Acoustic Guitar(ste bank #0preset #25
Instrument:Electric Guitar(jaz bank #0preset #26
Instrument:Electric Guitar(cle bank #0preset #27
Instrument:Electric Guitar(mut bank #0preset #28
Instrument:Overdriven Guitar bank #0preset #29
Instrument:Distortion Guitar bank #0preset #30
Instrument:Guitar harmonics bank #0preset #31
Instrument:Acoustic Bass bank #0preset #32
Instrument:Electric Bass(finge bank #0preset #33
Instrument:Electric Bass(pick)bank #0preset #34
Instrument:Fretless Bass bank #0preset #35
Instrument:Slap Bass 1bank #0preset #36
Instrument:Slap Bass 2bank #0preset #37
Instrument:Synth Bass 1bank #0preset #38
Instrument:Synth Bass 2bank #0preset #39
Instrument:Violin bank #0preset #40
Instrument:Viola bank #0preset #41
Instrument:Cello bank #0preset #42
Instrument:Contrabass bank #0preset #43
Instrument:Tremolo Strings bank #0preset #44
Instrument:Pizzicato Strings bank #0preset #45
Instrument:Orchestral Harp bank #0preset #46
Instrument:Timpani bank #0preset #47
Instrument:String Ensemble 1bank #0preset #48
Instrument:String Ensemble 2bank #0preset #49
Instrument:SynthStrings 1bank #0preset #50
Instrument:SynthStrings 2bank #0preset #51
Instrument:Choir Aahs bank #0preset #52
Instrument:Voice Oohs bank #0preset #53
Instrument:Synth Voice bank #0preset #54
Instrument:Orchestra Hit bank #0preset #55
Instrument:Trumpet bank #0preset #56
Instrument:Trombone bank #0preset #57
Instrument:Tuba bank #0preset #58
Instrument:Muted Trumpet bank #0preset #59
Instrument:French Horn bank #0preset #60
Instrument:Brass Section bank #0preset #61
Instrument:SynthBrass 1bank #0preset #62
Instrument:SynthBrass 2bank #0preset #63
Instrument:Soprano Sax bank #0preset #64
Instrument:Alto Sax bank #0preset #65
Instrument:Tenor Sax bank #0preset #66
Instrument:Baritone Sax bank #0preset #67
Instrument:Oboe bank #0preset #68
Instrument:English Horn bank #0preset #69
Instrument:Bassoon bank #0preset #70
Instrument:Clarinet bank #0preset #71
Instrument:Piccolo bank #0preset #72
Instrument:Flute bank #0preset #73
Instrument:Recorder bank #0preset #74
Instrument:Pan Flute bank #0preset #75
Instrument:Blown Bottle bank #0preset #76
Instrument:Shakuhachi bank #0preset #77
Instrument:Whistle bank #0preset #78
Instrument:Ocarina bank #0preset #79
Instrument:Lead 1(square)bank #0preset #80
Instrument:Lead 2(sawtooth)bank #0preset #81
Instrument:Lead 3(calliope)bank #0preset #82
Instrument:Lead 4(chiff)bank #0preset #83
Instrument:Lead 5(charang)bank #0preset #84
Instrument:Lead 6(voice)bank #0preset #85
Instrument:Lead 7(fifths)bank #0preset #86
Instrument:Lead 8(bass+lead)bank #0preset #87
Instrument:Pad 1(new age)bank #0preset #88
Instrument:Pad 2(warm)bank #0preset #89
Instrument:Pad 3(polysynth)bank #0preset #90
Instrument:Pad 4(choir)bank #0preset #91
Instrument:Pad 5(bowed)bank #0preset #92
Instrument:Pad 6(metallic)bank #0preset #93
Instrument:Pad 7(halo)bank #0preset #94
Instrument:Pad 8(sweep)bank #0preset #95
Instrument:FX 1(rain)bank #0preset #96
Instrument:FX 2(soundtrack)bank #0preset #97
Instrument:FX 3(crystal)bank #0preset #98
Instrument:FX 4(atmosphere)bank #0preset #99
Instrument:FX 5(brightness)bank #0preset #100
Instrument:FX 6(goblins)bank #0preset #101
Instrument:FX 7(echoes)bank #0preset #102
Instrument:FX 8(sci-fi)bank #0preset #103
Instrument:Sitar bank #0preset #104
Instrument:Banjo bank #0preset #105
Instrument:Shamisen bank #0preset #106
Instrument:Koto bank #0preset #107
Instrument:Kalimba bank #0preset #108
Instrument:Bag pipe bank #0preset #109
Instrument:Fiddle bank #0preset #110
Instrument:Shanai bank #0preset #111
Instrument:Tinkle Bell bank #0preset #112
Instrument:Agogo bank #0preset #113
Instrument:Steel Drums bank #0preset #114
Instrument:Woodblock bank #0preset #115
Instrument:Taiko Drum bank #0preset #116
Instrument:Melodic Tom bank #0preset #117
Instrument:Synth Drum bank #0preset #118
Instrument:Reverse Cymbal bank #0preset #119
Instrument:Guitar Fret Noise bank #0preset #120
Instrument:Breath Noise bank #0preset #121
Instrument:Seashore bank #0preset #122
Instrument:Bird Tweet bank #0preset #123
Instrument:Telephone Ring bank #0preset #124
Instrument:Helicopter bank #0preset #125
Instrument:Applause bank #0preset #126
Instrument:Gunshot bank #0preset #127*/
