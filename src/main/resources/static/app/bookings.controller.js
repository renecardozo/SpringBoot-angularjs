(function() {
    'use strict';
    angular
        .module('app')
        .controller('BookingsController', BookingsController)

    BookingsController.$inject = ['$http'];
    function BookingsController($http) {
        var vm = this;
        vm.bookings = [];
        vm.getAll = getAll;
        vm.getAffordable = getAffordable;
        vm.deleteBooking = deleteBooking;

        init();
        function init() {
            getAll();
        }
        function handleError (error) {
            console.log(error);
        }
        function handleResponse(response) {
            vm.bookings = response.data;
        }
        function getAll() {
            var url = '/bookings/all';
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(
                handleResponse,
                handleError
            );
        }
        function getAffordable(){
            var url = '/bookings/affordable/' + 100;
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(
                handleResponse,
                handleError
            );
        }
        function deleteBooking(idBooking) {
            var url = '/bookings/delete/' + idBooking;
            var bookingPromise = $http.post(url);
            bookingPromise.then(
                handleResponse,
                handleError
            )
        }
    }
})();