$( document ).ready(function() {
    //Adresse du web service
    var url = "http://localhost:8080/EnigmaRest/rest/enigma/";

	//Liste des joueurs
	$.ajax({
        url: url+"player",
        dataType: 'xml',
        type: "GET",
        success: function(xml){
        	$(xml).find('player').each(function(){
                var login = $(this).find('login').text();
                $('#listeJoueur ul').append("<li class=\"joueur\" id=\""+login+"\">"+login+"</li>");
            });
        },
        error: function(xhr, msg){
        	console.log("error");
        }
    });

    //details joueur
    $('body').on('click', '.joueur', function () {
         var loginJ = $(this).attr('id');
         $('#statjoueur').append("<h2>"+loginJ+"</h2>");

        $.ajax({
            url: url+"details",
            data: { login: loginJ },
            dataType: 'xml',
            type: "GET",
            success: function(xml){
                var x = xml.documentElement.childNodes;
                console.log(x.item(0));
                //Pour chaque questionReponse
                for(var i=0; i<x.lenght; i++){
                    var y = x.item(i);
                    console.log("bite");
                }


                // $(xml).find('questionReponse').each(function(){
                //     var question = $(this).find('question').text();
                //     var solution = $(this).find('queque').find('solution').text();
                //     var reponseJoueur = $(this).find('reponse').text();
                //     if(solution == reponseJoueur){
                //         $('#statjoueur').append("<div class=\"questionJoueur\"><h3>"+question+"</h3><p class=\"juste\">"+reponseJoueur+"</p></div>");
                //     }else{
                //         $('#statjoueur').append("<div class=\"questionJoueur\"><h3>"+question+"</h3><p class=\"faux\">"+reponseJoueur+"</p></div>");

                //     }
                // });
            },
            error: function(xhr, msg){
                console.log("error");
            }
        });
    });

});