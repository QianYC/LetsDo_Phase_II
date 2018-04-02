/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */


    $(document).ready(function () {
        var mainApp = {

            initFunction: function () {
                /*MENU
                ------------------------------------*/
                $('#main-menu').metisMenu();

                $(window).bind("load resize", function () {
                    if ($(this).width() < 768) {
                        $('div.sidebar-collapse').addClass('collapse')
                    } else {
                        $('div.sidebar-collapse').removeClass('collapse')
                    }
                });
            },

            initialization: function () {
                mainApp.initFunction();

            }
        }
        mainApp.initFunction(); 
		$("#sideNav").click(function(){
			if($(this).hasClass('closed')){
				$('.navbar-side').animate({left: '-260px'});
				$(this).removeClass('closed');
				$('#page-wrapper').animate({'margin-left' : '0px'});
				
			}
			else{
			    $(this).addClass('closed');
				$('.navbar-side').animate({left: '-0px'});
				$('#page-wrapper').animate({'margin-left' : '260px'});
			}
		});
    });

