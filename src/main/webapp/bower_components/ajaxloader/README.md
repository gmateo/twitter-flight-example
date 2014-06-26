AjaxLoader
==========

This is a jQuery plugin to show a loader on every ajax hit on the server with different theme.

How To Use
==========

You have to add these lines in you global page 

```
$(document).ready(function(){
	ajaxLoader.init();
});

```

different option for the loader are

```
ajaxLoader.init({
		'loader': 'dots',
		'theme' : 'grass',
		'size' : 10,
		});
		
```

possible value for the loader are 

```
    'bar',
    'moon', 
    'dots', 
    'pulser', 
    'commet', 
    'clock',
    '3dots',
    'hlaf_spin', 
    'circle'
```

possible value for theme are

```
  'apple',
  'dark', 
  'grass', 
  'sky',
  'love', 
  'girl',
  'chocolate',
  'gold'

```

value for size is 0 <  size < 75
