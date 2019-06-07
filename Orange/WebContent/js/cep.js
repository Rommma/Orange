$(document).ready(function(){
	function limpa_formulario_cep() {
        // Limpa valores do formulário de cep.
        $("#logradouro").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#estado").val("");
    }
	 $("#cep").blur(function() {
		 var cep = $(this).val().replace(/\D/g, '');
		
		 if (cep != "") {
			 //var validacep = /^[0-9]{8}$/;
             
             //if(validacep.test(cep)) {
                 
	             $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
	            	 if (!("erro" in dados)) {
	                     //Atualiza os campos com os valores da consulta.
	                     $("#logradouro").val(dados.logradouro);
	                     $("#bairro").val(dados.bairro);
	                     $("#cidade").val(dados.localidade);
	                     $("#estado").val(dados.uf);
	            	 }else{
	            		 limpa_formulario_cep();
	                     alert("CEP não encontrado");
	            	 }
	             });
            /* }else{
            	 limpa_formulario_cep();
                 alert("CEP inválido");
             }*/
		 }
	 });
});