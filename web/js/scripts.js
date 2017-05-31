
$(document).ready(function(){/* smooth scrolling for scroll to top */
$('.scroll-top').click(function(){
  $('body,html').animate({scrollTop:0},800);
});
/* smooth scrolling for scroll down */
$('.scroll-down').click(function(){
  $('body,html').animate({scrollTop:$(window).scrollTop()+800},1000);
});

$('[data-toggle=collapse]').click(function(){
  	// toggle icon
  	$(this).find("i").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
});

/* highlight the top nav as scrolling occurs */
$('body').scrollspy({ target: '#navbar' });
});