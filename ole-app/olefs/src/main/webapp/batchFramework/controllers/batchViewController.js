var app = angular.module('batchProcessProfile', ['ngAnimate', 'ngSanitize', 'mgcrea.ngStrap']);

app.controller('batchProfileController', ['$scope', '$http', function ($scope, $http) {
    $scope.booleanOptions = booleanOptions;
    $scope.submitted = false;
    $scope.mainSectionActivePanel = [0];
    $scope.matchPointsActivePanel = [];
    $scope.addOrOverlayActivePanel = [];
    $scope.fieldOperationsActivePanel = [];
    $scope.dataMappingsActivePanel = [];
    $scope.dataTransformationsActivePanel = [];
    $scope.constantsAndDefaultsActivePanel = [];
    $scope.mainSectionPanel = mainSection;

    $scope.setValuesForBatchProcessType = function (mainSectionPanel) {
        if (mainSectionPanel.batchProcessType == 'Bib Import') {
            $scope.matchPointsPanel = [matchPoint];
            $scope.addOrOverlayPanel = [addOrOverlay];
            $scope.dataMappingsPanel = [dataMapping];
            $scope.dataTransformationsPanel = [dataTransformation];
            $http.get(OLENG_CONSTANTS.PROFILE_GET_GLOBALLY_PROTECTED_FIELDS).success(function (data) {
                $scope.fieldOperationsPanel = data;
                $scope.fieldOperationsPanel.unshift(fieldOperation);
            });
            $scope.mainSectionPanel.bibImportProfileForOrderImport = null;
            $scope.matchPointsActivePanel = [];
            $scope.addOrOverlayActivePanel = [];
            $scope.fieldOperationsActivePanel = [];
            $scope.dataMappingsActivePanel = [];
            $scope.dataTransformationsActivePanel = [];
            $scope.mainSectionPanel.requisitionForTitlesOption = null;
            $scope.mainSectionPanel.marcOnly = false;
            $scope.dataMappingsPanel[0].dataMappingDocType = 'Bibliographic';
            $scope.dataMappingsPanel[0].dataField = null;
            $scope.dataMappingsPanel[0].ind1 = null;
            $scope.dataMappingsPanel[0].ind2 = null;
            $scope.dataMappingsPanel[0].subField = null;
            $scope.dataMappingsPanel[0].constant = null;
            $scope.dataMappingsPanel[0].destination = null;
            $scope.dataMappingsPanel[0].field = null;
            $scope.dataMappingsPanel[0].priority = 1;
            $scope.constantsAndDefaultsPanel = [];
        } else if (mainSectionPanel.batchProcessType == 'Order Record Import') {
            $scope.mainSectionPanel.bibImportProfileForOrderImport = null;
            if ($scope.bibImportProfileNames == undefined) {
                $http.get(OLENG_CONSTANTS.PROFILE_GET_NAMES).success(function (data) {
                    $scope.bibImportProfileNames = data;
                });
            }
            $scope.mainSectionPanel.requisitionForTitlesOption = 'One Requisition Per Title';
            $scope.constantsAndDefaultsPanel = [constantAndDefault];
            $scope.dataMappingsPanel = [dataMapping];
            $scope.matchPointsPanel = [];
            $scope.addOrOverlayPanel = [];
            $scope.fieldOperationsPanel = [];
            $scope.dataTransformationsPanel = [];
            $scope.constantsAndDefaultsActivePanel = [];
            $scope.dataMappingsActivePanel = [];
            $scope.constantsAndDefaultsPanel[0].fieldName = null;
            $scope.constantsAndDefaultsPanel[0].fieldValue = null;
            $scope.dataMappingsPanel[0].dataMappingDocType = null;
            $scope.dataMappingsPanel[0].dataField = null;
            $scope.dataMappingsPanel[0].ind1 = null;
            $scope.dataMappingsPanel[0].ind2 = null;
            $scope.dataMappingsPanel[0].subField = null;
            $scope.dataMappingsPanel[0].constant = null;
            $scope.dataMappingsPanel[0].destination = null;
            $scope.dataMappingsPanel[0].field = null;
            $scope.dataMappingsPanel[0].priority = 1;
        }
    };

    $scope.matchPointAdd = function () {
        $scope.matchPointsPanel.push({
            matchPointDocType: $scope.matchPointsPanel[0].matchPointDocType,
            matchPointType: $scope.matchPointsPanel[0].matchPointType,
            matchPointValue: $scope.matchPointsPanel[0].matchPointValue,
            dataField: $scope.matchPointsPanel[0].dataField,
            ind1: $scope.matchPointsPanel[0].ind1,
            ind2: $scope.matchPointsPanel[0].ind2,
            subField: $scope.matchPointsPanel[0].subField,
            constant: $scope.matchPointsPanel[0].constant,
            isAddLine: true
        });
        $scope.matchPointsPanel[0].matchPointDocType = 'Bibliographic';
        $scope.matchPointsPanel[0].matchPointType = null;
        $scope.matchPointsPanel[0].matchPointValue = null;
        $scope.matchPointsPanel[0].dataField = null;
        $scope.matchPointsPanel[0].ind1 = null;
        $scope.matchPointsPanel[0].ind2 = null;
        $scope.matchPointsPanel[0].subField = null;
        $scope.matchPointsPanel[0].constant = null;
    };

    $scope.matchPointRemove = function (matchPoint) {
        var index = $scope.matchPointsPanel.indexOf(matchPoint);
        $scope.matchPointsPanel.splice(index, 1);
    };

    $scope.addOrOverlayAdd = function () {
        $scope.addOrOverlayPanel.push({
            matchOption: $scope.addOrOverlayPanel[0].matchOption,
            addOrOverlayDocType: $scope.addOrOverlayPanel[0].addOrOverlayDocType,
            operation: $scope.addOrOverlayPanel[0].operation,
            bibStatus: $scope.addOrOverlayPanel[0].bibStatus,
            addOperation: $scope.addOrOverlayPanel[0].addOperation,
            addItems: $scope.addOrOverlayPanel[0].addItems,
            isAddLine: true
        });
        $scope.addOrOverlayPanel[0].matchOption = 'If Match Found';
        $scope.addOrOverlayPanel[0].addOrOverlayDocType = 'Bibliographic';
        $scope.addOrOverlayPanel[0].operation = 'Add';

    };

    $scope.addOrOverlayRemove = function (addOrOverlay) {
        var index = $scope.addOrOverlayPanel.indexOf(addOrOverlay);
        $scope.addOrOverlayPanel.splice(index, 1);
    };

    $scope.fieldOperationAdd = function () {
        $scope.fieldOperationsPanel.push({
            fieldOperationType: $scope.fieldOperationsPanel[0].fieldOperationType,
            dataField: $scope.fieldOperationsPanel[0].dataField,
            ind1: $scope.fieldOperationsPanel[0].ind1,
            ind2: $scope.fieldOperationsPanel[0].ind2,
            subField: $scope.fieldOperationsPanel[0].subField,
            ignoreGPF: false,
            isAddLine: true
        });
        $scope.fieldOperationsPanel[0].fieldOperationType = 'Profile Protected Field';
        $scope.fieldOperationsPanel[0].dataField = null;
        $scope.fieldOperationsPanel[0].ind1 = null;
        $scope.fieldOperationsPanel[0].ind2 = null;
        $scope.fieldOperationsPanel[0].subField = null;
        $scope.fieldOperationsPanel[0].ignoreGPF = false;
    };

    $scope.fieldOperationRemove = function (fieldOperation) {
        var index = $scope.fieldOperationsPanel.indexOf(fieldOperation);
        $scope.fieldOperationsPanel.splice(index, 1);
    };

    $scope.dataMappingAdd = function () {
        $scope.dataMappingsPanel.push({
            dataMappingDocType: $scope.dataMappingsPanel[0].dataMappingDocType,
            dataField: $scope.dataMappingsPanel[0].dataField,
            ind1: $scope.dataMappingsPanel[0].ind1,
            ind2: $scope.dataMappingsPanel[0].ind2,
            subField: $scope.dataMappingsPanel[0].subField,
            constant: $scope.dataMappingsPanel[0].constant,
            destination: $scope.dataMappingsPanel[0].destination,
            field: $scope.dataMappingsPanel[0].field,
            priority: $scope.dataMappingsPanel[0].priority,
            isAddLine: true
        });
        $scope.dataMappingsPanel[0].dataMappingDocType = 'Bibliographic';
        $scope.dataMappingsPanel[0].dataField = null;
        $scope.dataMappingsPanel[0].ind1 = null;
        $scope.dataMappingsPanel[0].ind2 = null;
        $scope.dataMappingsPanel[0].subField = null;
        $scope.dataMappingsPanel[0].constant = null;
        $scope.dataMappingsPanel[0].destination = null;
        $scope.dataMappingsPanel[0].field = null;
        $scope.dataMappingsPanel[0].priority = 1;
    };

    $scope.dataMappingRemove = function (dataMapping) {
        var index = $scope.dataMappingsPanel.indexOf(dataMapping);
        $scope.dataMappingsPanel.splice(index, 1);
    };

    $scope.dataTransformationAdd = function () {
        $scope.dataTransformationsPanel.push({
            dataTransformationDocType: $scope.dataTransformationsPanel[0].dataTransformationDocType,
            dataTransformationActionType: $scope.dataTransformationsPanel[0].dataTransformationActionType,
            dataTransformationAction: $scope.dataTransformationsPanel[0].dataTransformationAction,
            dataTransformationField: $scope.dataTransformationsPanel[0].dataTransformationField,
            dataTransformationFieldValue: $scope.dataTransformationsPanel[0].dataTransformationFieldValue,
            dataTransformationSourceField: $scope.dataTransformationsPanel[0].dataTransformationSourceField,
            dataTransformationOperation: $scope.dataTransformationsPanel[0].dataTransformationOperation,
            dataTransformationDestinationField: $scope.dataTransformationsPanel[0].dataTransformationDestinationField,
            dataTransformationConstant: $scope.dataTransformationsPanel[0].dataTransformationConstant,
            dataTransformationTransformField: $scope.dataTransformationsPanel[0].dataTransformationTransformField,
            dataTransformationStep: $scope.dataTransformationsPanel[0].dataTransformationStep,
            isAddLine: true
        });
        $scope.dataTransformationsPanel[0].dataTransformationDocType = 'Bibliographic';
        $scope.dataTransformationsPanel[0].dataTransformationActionType = 'All';
        $scope.dataTransformationsPanel[0].dataTransformationAction = 'Add';
        $scope.dataTransformationsPanel[0].dataTransformationField = null;
        $scope.dataTransformationsPanel[0].dataTransformationFieldValue = null;
        $scope.dataTransformationsPanel[0].dataTransformationSourceField = null;
        $scope.dataTransformationsPanel[0].dataTransformationOperation = null;
        $scope.dataTransformationsPanel[0].dataTransformationDestinationField = null;
        $scope.dataTransformationsPanel[0].dataTransformationConstant = null;
        $scope.dataTransformationsPanel[0].dataTransformationTransformField = null;
        $scope.dataTransformationsPanel[0].dataTransformationStep = 1;
    };

    $scope.dataTransformationRemove = function (dataTransformation) {
        var index = $scope.dataTransformationsPanel.indexOf(dataTransformation);
        $scope.dataTransformationsPanel.splice(index, 1);
    };

    $scope.constantsAndDefaultAdd = function () {
        $scope.constantsAndDefaultsPanel.push({
            fieldName: $scope.constantsAndDefaultsPanel[0].fieldName,
            fieldValue: $scope.constantsAndDefaultsPanel[0].fieldValue,
            constantOrDefault: $scope.constantsAndDefaultsPanel[0].constantOrDefault,
            isAddLine: true
        });
        $scope.constantsAndDefaultsPanel[0].fieldName = null;
        $scope.constantsAndDefaultsPanel[0].fieldValue = null;
        $scope.constantsAndDefaultsPanel[0].constantOrDefault = 'Constant';
    };

    $scope.constantsAndDefaultRemove = function (constantsAndDefault) {
        var index = $scope.constantsAndDefaultsPanel.indexOf(constantsAndDefault);
        $scope.constantsAndDefaultsPanel.splice(index, 1);
    };

    $scope.setDefaultsMatchPoint = function (matchPoint) {
        matchPoint.matchPointType = null;
        matchPoint.dataField = null;
        matchPoint.ind1 = null;
        matchPoint.ind2 = null;
        matchPoint.subField = null;
    };

    $scope.setDefaultsDataTransformation = function (dataTransformation) {
        dataTransformation.dataTransformationActionType = 'All';
        dataTransformation.dataTransformationAction = 'Add';
        dataTransformation.dataTransformationField = null;
        dataTransformation.dataTransformationFieldValue = null;
        dataTransformation.dataTransformationSourceField = null;
        dataTransformation.dataTransformationOperation = null;
        dataTransformation.dataTransformationDestinationField = null;
        dataTransformation.dataTransformationTransformField = null;
    };

    $scope.setDefaultsAction = function (dataTransformation) {
        dataTransformation.dataTransformationField = null;
    };

    $scope.setDefaultsDestination = function (dataMapping) {
        dataMapping.field = null;
    };

    $scope.getMaintenanceValuesForType = function (dataObject) {
        getMaintenanceData(dataObject, $scope, $http);
    };

    $scope.submit = function () {
        $scope.submitted = true;
        removeEmptyValues();
        var profile = {
            "profileId": $scope.mainSectionPanel.profileId,
            "profileName": $scope.mainSectionPanel.profileName,
            "description": $scope.mainSectionPanel.profileDescription,
            "batchProcessType": $scope.mainSectionPanel.batchProcessType,
            "bibImportProfileForOrderImport": $scope.mainSectionPanel.bibImportProfileForOrderImport,
            "requisitionForTitlesOption": $scope.mainSectionPanel.requisitionForTitlesOption,
            "marcOnly": $scope.mainSectionPanel.marcOnly,
            "forceLoad": $scope.matchPointsActivePanel.forceLoad,
            "batchProfileMatchPointList": $scope.matchPointsPanel,
            "batchProfileAddOrOverlayList": $scope.addOrOverlayPanel,
            "batchProfileFieldOperationList": $scope.fieldOperationsPanel,
            "batchProfileDataMappingList": $scope.dataMappingsPanel,
            "batchProfileDataTransformerList": $scope.dataTransformationsPanel,
            "batchProfileConstantAndDefaultList": $scope.constantsAndDefaultsPanel
        };
        $http.post(OLENG_CONSTANTS.PROFILE_SUBMIT, profile)
            .success(function (data) {
                $scope.profile = data;
                $scope.message = 'Document was successfully submitted.';
            });
    };

    $scope.init = function () {
        //JSON.stringify(vars)
        var urlVars = getUrlVars();
        var profileId = urlVars['profileId'];
        var action = urlVars['action'];
        if (profileId !== null && profileId !== undefined && profileId !== '') {
            var data = {};
            data["profileId"] = profileId;
            data["action"] = action;
            $http.post(OLENG_CONSTANTS.PROFILE_EDIT, JSON.stringify(data))
                .success(function (data) {
                    $scope.profile = data;
                    $scope.mainSectionPanel.profileId = data.profileId;
                    $scope.mainSectionPanel.profileName = data.profileName;
                    $scope.mainSectionPanel.profileDescription = data.description;
                    $scope.mainSectionPanel.batchProcessType = data.batchProcessType;
                    $scope.mainSectionPanel.bibImportProfileForOrderImport = data.bibImportProfileForOrderImport;
                    $scope.mainSectionPanel.requisitionForTitlesOption = data.requisitionForTitlesOption;
                    $scope.mainSectionPanel.marcOnly = data.marcOnly;
                    $scope.matchPointsActivePanel.forceLoad = data.forceLoad;
                    $scope.matchPointsPanel = data.batchProfileMatchPointList;
                    $scope.addOrOverlayPanel = data.batchProfileAddOrOverlayList;
                    $scope.fieldOperationsPanel = data.batchProfileFieldOperationList;
                    $scope.dataMappingsPanel = data.batchProfileDataMappingList;
                    $scope.dataTransformationsPanel = data.batchProfileDataTransformerList;
                    $scope.constantsAndDefaultsPanel = data.batchProfileConstantAndDefaultList;

                    addEmptyValueToAddNew(data.batchProcessType);

                    if (data.batchProcessType == 'Order Record Import' && $scope.bibImportProfileNames == undefined) {
                        $http.get(OLENG_CONSTANTS.PROFILE_GET_NAMES).success(function (data) {
                            $scope.bibImportProfileNames = data;
                        });
                    }
                });
        }
    };

    $scope.cancel = function () {
        window.location = OLENG_CONSTANTS.PROFILE_CANCEL;
    };

    var removeEmptyValues = function () {
        $scope.matchPointsPanel.splice(0, 1);
        $scope.addOrOverlayPanel.splice(0, 1);
        $scope.fieldOperationsPanel.splice(0, 1);
        $scope.dataMappingsPanel.splice(0, 1);
        $scope.dataTransformationsPanel.splice(0, 1);
        if ($scope.mainSectionPanel.batchProcessType == 'Order Record Import') {
            $scope.constantsAndDefaultsPanel.splice(0, 1);
        }
    };

    var addEmptyValueToAddNew = function (batchProcessType) {
        $scope.matchPointsPanel.unshift(matchPoint);
        $scope.addOrOverlayPanel.unshift(addOrOverlay);
        $scope.fieldOperationsPanel.unshift(fieldOperation);
        $scope.dataMappingsPanel.unshift(dataMapping);
        $scope.dataTransformationsPanel.unshift(dataTransformation);
        if (batchProcessType == 'Order Record Import') {
            $scope.constantsAndDefaultsPanel.unshift(constantAndDefault);
        }
    };

    var getUrlVars = function () {
        var vars = {}, hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars[hash[0]] = hash[1];
        }
        return vars;
    }
}]);