# Datatypes in javasnes

This is a list of the datatypes in javasnes, with their names and uses:

| Datatype    | Name | Usage                                                                                 |
| ----------- | ---- | ------------------------------------------------------------------------------------- |
| DataBrr     | Brr  | Use DataBrr to store BRR sound files, make then with audio-tools from SNES-IDE compile with audio-tools from SNES-IDE or snesbrr from pvsneslib. |
| DataIT      | IT   | Use DataIT to store IT sound files, make them with schismtracker or openMPT, it is compiled in bnk format in execution time. |
| DataMap     | Map  | Use DataMap to store Map files, which is used for bg images in snes, make them with graphic-tools from SNES-IDE or gfx4snes from pvsneslib. |
| DataPic     | Pic  | Use DataPic to store Pic files, which is used for any image in snes development, make them with graphic-tools from SNES-IDE or gfx4snes from pvsneslib. |
| DataPal     | Pal  | Use DataPal to store Pal files, which is used for palettes in snes, make them with graphic-tools from SNES-IDE or gfx4snes from pvsneslib. |

## DataFolder

Using DataFolder we can organize data files such as Brr, IT, Map, Pic, or Pal in the javasnes project data directory.

You can use it to declare DataFolder objects that represent the folder where data files are stored.

Example:

```java

DataFolder myAssets = new DataFolder("/home/user/myproject/assets");

```

Or you can use more than one DataFolder:

```java

DataFolder myITFolder = new DataFolder("/home/user/myproject/itFolder");
DataFolder myBrrFolder = new DataFolder("/home/user/myproject/brrFolder");

```

It is necessary when you want to use DataBrr, DataIT, DataMap, DataPic, DataPal use DataFolder.

```java

DataBrr myBrr = new DataBrr(myBrrFolder, "myBrr.brr");

```

## Datas

Using Datas we can store data files such as Brr, IT, Map, Pic, or Pal in the javasnes project data directory.

### DataBrr

Use DataBrr to store BRR sound files, use it for short samples like in [pvsneslib tada example](https://github.com/pvsneslib/pvsneslib/blob/master/src/tada.c).

It uses a 16 bit signed PCM: **32 bytes** -> Brr: **9 bytes** compression.

Usage example:

```java

DataBrr myBrr = new DataBrr(myBrrFolder, "myBrr.brr");

```

We have 32 banks of 32 KiB eack, so 1024 bytes per bank, you can pre-determinate the size of the data file using DataBrr, which is very useful for pre-determinate how much DataBrr you can put in a bank or how much banks for some amount of brr files.

Usage example:

```java

DataBrr myBrr = new DataBrr(myBrrFolder, "myBrr.brr", 7345);  // 7345 bytes

```

Using Javasnes you can only use Brr files which are less than 32 KiB, so if you want to use more than 32 KiB you need to use DataIT with Impulse Tracker files instead.

### DataIT

TODO...

### DataMap

TODO...

### DataPic

TODO...

### DataPal

TODO...

### Abstract Data class

You can use Data to declare Data objects of different types of data files. For example:

```java

DataFolder assetFolder = new DataFolder("/home/user/myproject/assets");

Data myBrr = new DataBrr(assetFolder, "myBrrSample.brr");
Data myIT = new DataIT(assetFolder, "myITMusic.it");
Data myMap = new DataMap(assetFolder, "myMapBG.map");
// ...

```
