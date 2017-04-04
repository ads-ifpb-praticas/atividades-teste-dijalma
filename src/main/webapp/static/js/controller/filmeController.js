/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 09:05
 */

app.controller('FilmeController', ['$scope', '$http', function ($scope, $http) {

    $scope.filme = {};
    $scope.filmes = [];
    $scope.notificationText = "";
    var isCadastro = false;

    $scope.findAllFilmes = function () {
        $http.get('/filmes').then(function (response) {
            if (response.status === 200) {
                $scope.notificationText = "Listando todos os filmes";
                $scope.filmes = response.data;
                console.log($scope.filmes);
            } else {
                $scope.notificationText = "Não foram encontrados os filmes.";
                $scope.filmes = [];
            }
        });
    };

    $scope.findAllFilmes();

    $scope.getCadastro = function () {
        return isCadastro;
    };

    $scope.setCadastro = function (newCadastro) {
        if (newCadastro === null) {
            isCadastro = true;
        } else {
            isCadastro = newCadastro;
        }
    };

    $scope.saveFilme = function () {
        $http.post("/filmes", $scope.filme).then(function (response) {
            console.log(response);
            if (response.status === 200) {
                $scope.notificationText = "Filme cadastrado com sucesso!";
            } else {
                $scope.notificationText = response.data;
            }
        });
    };

    $scope.deleteFilme = function (id) {
        $http.delete("/filmes/" + id).then(function (response) {
            if (response.status === 200) {
                $scope.notificationText = "Filme excluído com sucesso!";
            } else {
                $scope.notificationText = "Não foi possível excluir o filme!";
            }
        });
    };

    $scope.updateFilme = function () {
        $http.put("/filmes/" + $scope.filme.id, $scope.filme).then(function (response) {
            if (response.status === 200) {
                $scope.notificationText = "Filme atualizado!";
            } else {
                $scope.notificationText = "Filme não pode ser atualizado!";
            }
        });
    };

    $scope.findFilmeById = function (id) {
        $http.get("/filmes/" + id).then(function (response) {
            if (response.status === 200) {
                $scope.notificationText = "Filme encontrado!";
                $scope.filme = response.data;
            } else {
                $scope.notificationText = "Filme não encontrado!";
                $scope.filme = [];
            }
        });
    };

    $scope.disponivel = function (estado) {
        return estado === 'DISPONIVEL';
    }
}]);