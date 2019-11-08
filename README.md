# If JavaFX isn't working in the project, try this in Eclipse:

1) Right click the project folder
2) Go down to Properties and left click it.
3) Go to Java Build Path
4) In the window where it says JRE System Libraray, click the drop down arrow.
5) Go to Access Rules, should be the first item.
6) On the right hand side, click Edit...
7) In that window, on the right hand side click Add...
8) In the field that says Rule Pattern paste this into it: javafx/**
9) Above that, where it says Resolution, click the drop down and select Accessible, then click OK
10) Click OK again in the previous window.
11) Then at the bottom, click Apply and Close, it should work now and not give errors.