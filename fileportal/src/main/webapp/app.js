var app = angular.module('ToDoListApp', [
	'ngRoute',
	'ngResource',
	'ui.bootstrap', 
	'ui.bootstrap.datetimepicker',
	'toDoListApp.w3TestDirective',
	'toDoListApp.fileModel',
	'myApp.tableDirective',
	'myApp.hightableDirective',
	'tableExample',
	'myDirectives'
	])

	//Define routes to cashmgmt web pages.
	app
	.config(function($routeProvider){
				$routeProvider
				.when('/',
				{
					controller: 'ListTasksController',
					templateUrl: '/views/tasks.html'
				})
				.when('/hello',
				{
					controller: 'HelloController',
					templateUrl: '/views/hello/hello.html'
				})
				.when('/hightable',
				{
					controller: 'HighTableController',
					templateUrl: '/views/hightable/hightable.html'
				})
				.when('/there',
				{
					controller: 'ThereController',
					templateUrl: '/views/there/there.html'
				})
				.when('/fileupload',
				{
					controller: 'FileUploadController',
					templateUrl: '/views/fileupload/fileupload.html'
				})
				.when('/demo',
				{
					controller: 'DemoController',
					templateUrl: '/views/demo/demo.html'
				})
				.when('/heroes',
				{
					controller: 'HeroesController',
					templateUrl: '/views/heroes/heroes.html'
				})
				.when('/addTask',
				{
					controller: 'AddTasksController',
					templateUrl: '/views/addTasks.html'
				})
				.when('/editTask:id',
				{
					controller: 'EditTasksController',
					templateUrl: '/views/editTasks.html'
				})
				.when('/completedTasks',
				{
					controller: 'ListTasksController',
					templateUrl: '/views/completedTasks.html'
				})				
				.otherwise({
					redirectTo: '/#'
				});
		});
		