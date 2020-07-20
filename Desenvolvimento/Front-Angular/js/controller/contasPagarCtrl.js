angular.module('contasPagar').controller('contasPagarCtrl', function ($scope, $http, $filter) {
    $scope.app="Contas a Pagar";
    $scope.contas=[];
    
    $scope.adicionarConta = function(conta) {
        conta.dataVencimento = $filter('date')(conta.dataVencimento, 'dd/MM/yyyy');
        conta.dataPagamento = $filter('date')(conta.dataPagamento, 'dd/MM/yyyy');
        $http.post('http://localhost:8080/despesas', conta).then(function(){
            carregarContas();
            delete $scope.conta;
        }), function(erros){
            console.log(erros);
        };
    };
        var carregarContas = function () {
        $http.get('http://localhost:8080/despesas').then(function (response) {
        $scope.contas = response.data;
        $scope.contas.forEach(element => {
            element.dataPagamento = $filter('date')(element.dataPagamento, 'dd/MM/yyyy');
        });
    }), function(erros) {
        //some error
        console.log(erros);
        };
    };
  carregarContas();  
});