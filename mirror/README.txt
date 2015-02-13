# Update notes
A sample project using Dagger. The codebase is updated to make it previewable in [Mirror Sandbox](jimulabs.com/2015/01/building-android-animations-mirror-sandbox-piecewise/).  Related changes are in [these commits](https://github.com/jimulabs/u2020-mirror/compare/0a7b747...742b3d25e35391cb6e4130bd2a20cc859094cdd0).

Below is the original README.


Mirror Directory Layout
-----------------------------------------------

The layout of "mirror" directory has be modified to support sample
data generation for different build variants. Files originally located
in "mirror/" has be moved to "mirror/main/" and variant specific
directories will be created as necessary to store your variant specific
screen (.xml) files for Mirror. We will no longer send over files
directly under "mirror/" any more. Also, "mirror/res" is now renamed
into "mirror/.res" to distinguish between sample layouts and resources.
If you do not use any flavor, build type or variant specific sourceset
for your project, the rest of this file can be ignored.

Below is an example of the new directory structure:

    mirror/
        .res/
        .gen/
        debug/
            build_type.xml
        free/
            variant.xml
        freeDebug/
            variant.xml
        main/
            activity_main.xml
            image.png
            sample.xml
        paid/
            image.png
            sample.xml
            variant.xml
        paidDebug/
            flavor.xml
            image.png
        paidRelease/
            image.png
        release/
            build_type.xml
            sample.xml

If your selected variant is paidRelease, then "paidRelease/image.png",
"release/build_type.xml", "release/sample.xml", "paid/variant.xml",
"main/activity_main.xml" will be pushed to the device.

More generally, the merging process and overriding behaviour of the screen
directories resemble how Android Studio deal with different sourcesets.
In the order of higher to lower priority, we have:

               variant > build type > flavor > main

We employ a simple way of merging the files in mirror screen directories:
Only directories related to the current selected flavor will be considered
and if files with the same name or relative path exist in different
directories, only the file in the directory with the highest priority will
be included, the rest will be ignored.

Here's a more generalized directory structure:

    mirror/
        .res/
            <mirror specific resources that you can add in>
        .gen/
            <generated files that let Mirror uses>
        main/
            <generated screen files with sample data>
            <files or directories you put here to help you preview>
        <flavor specific directories>
        <build type specific directories>
        <variant specific directories>
