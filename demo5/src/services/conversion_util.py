import os
import subprocess
import sys
import json
import re
import xmltodict
from src.models.licenseSummaryDTO import licenseSummary
from src.models.dependenciesDTO import dependencies
from src.models.dependencyDTO import dependency
from src.models.licenseDTO import license


executions = ['maven', 'gradle', 'angular', 'python']
executions_ = [{'name': 'maven', 'path_file': 'mvnw'}, {'name': 'gradle', 'path_file': 'gradle'}, {'name': 'angular', 'path_file': 'angular.json'}]
project_path = 'tests/'


def create_dto_maven(_list):
    dependencies_dto = dependencies()
    for iterator in _list['licenseSummary']['dependencies']['dependency']:
        licenses_dto_list = list()
        license_object = iterator['licenses']['license']
        if type(license_object) == list:
            for license_iterator in license_object:
                license_iterator['distribution'] = '' if 'distribution' not in license_iterator else license_iterator['distribution']
                license_iterator['url'] = '' if 'url' not in license_iterator else license_iterator['url']
                license_iterator['file'] = '' if 'file' not in license_iterator else license_iterator['file']
                license_dto = license(license_iterator['url'], license_iterator['name'], license_iterator['distribution'], license_iterator['file'])
                licenses_dto_list.append(license_dto)
        elif type(license_object) == dict:
            license_object['distribution'] = '' if 'distribution' not in license_object else license_object['distribution']
            license_object['url'] = '' if 'url' not in license_object else license_object['url']
            license_object['file'] = '' if 'file' not in license_object else license_object['file']
            license_dto = license(license_object['url'], license_object['name'], license_object['distribution'], license_object['file'])
            licenses_dto_list.append(license_dto)

        dependency_dto = dependency(iterator['groupId'],  iterator['artifactId'], iterator['version'], licenses_dto_list)
        dependencies_dto.setDependency(dependency_dto)
    
    license_summary_dto = licenseSummary(dependencies_dto)

    print(license_summary_dto)
    return license_summary_dto


def create_dto_gradle(_list):
    dependencies_dto = dependencies()
    for iterator in _list:
        licenses_dto_list = list()
        
        temp_split_text = iterator[0].split(' - ')
        temp_split_text = temp_split_text[1].split(':')
        temp_split_text2 = iterator[2].split(' - ')

        license_dto = license(temp_split_text2[0], temp_split_text2[1], 'repo', '')
        licenses_dto_list.append(license_dto)
        dependency_dto = dependency(temp_split_text[0],  temp_split_text[1], iterator[1], licenses_dto_list)
        dependencies_dto.setDependency(dependency_dto)
    
    license_summary_dto = licenseSummary(dependencies_dto)

    print(license_summary_dto)
    return license_summary_dto


def create_dto_angular(_list):
    dependencies_dto = dependencies()
    for iterator in _list:
        licenses_dto_list = list()

        license_dto = license(iterator['link'], iterator['licenseType'], iterator['author'], iterator['licensePeriod'])
        licenses_dto_list.append(license_dto)
        dependency_dto = dependency(iterator['name'],  iterator['name'], iterator['installedVersion'], licenses_dto_list)
        dependencies_dto.setDependency(dependency_dto)
    
    license_summary_dto = licenseSummary(dependencies_dto)

    print(license_summary_dto)
    return license_summary_dto


def split_by_regex(regex, target_string):
    match = re.search(regex, target_string)
    return match.group() if match else ''


def format_html_to_json(str_html):
    str_html = str_html.replace(",", "")
    str_html = str_html.replace("\"", "'")
    str_html = str_html.replace("<table>", "[")
    str_html = str_html.replace("</table>", "]")
    str_html = str_html.replace("<tr>", "[")
    str_html = str_html.replace("</tr>", "],")
    str_html = str_html.replace("</tr>", "]")
    str_html = str_html.replace("<a href='", "")
    str_html = str_html.replace("'>", " - ")
    str_html = str_html.replace("</a>", "")
    str_html = str_html.replace("<td>", "\"")
    str_html = str_html.replace("</td>", "\",")
    str_html = str_html.replace(",],[", "],[")
    str_html = str_html.replace(",],]", "]]")

    return str_html


def handler_gradle(path):
    lines = open_file(path).splitlines()
    lines_joined = join_list_by_delimiter("," , lines)
    str_found_by_regex = split_by_regex(r"\<table>(.*)\</table>", lines_joined)
    str_formatted = format_html_to_json(str_found_by_regex)
    licenses_object = cast_str_to_dict(str_formatted)
    licenses_object.pop(0)
    create_dto_gradle(licenses_object)


def handler_maven(path):
    lines = open_file(path)
    licenses_object = xmltodict.parse(lines)
    create_dto_maven(licenses_object)


def handler_angular(path):
    print(path)
    lines = open(path)
    licenses_object = json.load(lines)
    create_dto_angular(licenses_object)


def handler(execution, path_to_file):
    if execution == 'gradle':
        handler_gradle(path_to_file)
    elif execution == 'maven':
        handler_maven(path_to_file)
    elif execution == 'angular':
        handler_angular(path_to_file)
    else:
        return {'statusCode': 400, 'statusMessage': 'Error: Operation not Allowed'}


def join_list_by_delimiter(delimiter, list):
    return delimiter.join(list)


def cast_str_to_dict(str_dict):
    return json.loads(str_dict)


def open_file(path):
    lines = []
    with open(path) as f:
        lines = f.read()
        
    return lines


def read_lines_file(file):  
    return file.read()


def validate_execution(path):
    file_gradle = 'build.gradle'
    file_maven = 'pom.xml'
    file_angular = 'angular.json'

    path_gradle = path + '/' + file_gradle
    path_maven = path + '/' + file_maven
    path_angular = path + '/' + file_angular

    is_gradle = os.path.exists(path_gradle)
    is_maven = os.path.exists(path_maven)
    is_angular = os.path.exists(path_angular)

    if is_gradle:
        return 'gradle'
    elif is_maven: 
        return 'maven'
    elif is_angular:
        return 'angular'
    else:
        return ''


def generate_licenses_reports(execution, project_name):
    if execution == 'gradle':
        os.system(f'cmd /c echo iniciando generacion de licencias gradle & cd {project_path}{project_name} & gradle generateLicenseReport')
    elif execution == 'maven':
        os.system(f'cmd /c echo iniciando generacion de licencias maven & cd {project_path}{project_name} & mvn license:aggregate-download-licenses')
    elif execution == 'angular':
        os.system(f'cmd /c echo iniciando generacion de licencias angular & cd {project_path}{project_name} & npm install & npm i -g license-report & npm i -g hcat & license-report --output=json --config licenses.json > licenses.json')
    else:
        print('no execution founded')


def format_path(path: str):
    path = path.replace('\\', '/')
    path = path.replace('/gradle', '')
    path = path.replace('/mvnw', '')
    path = path.replace('/angular.json', '')

    return path


def get_project_names(arr_paths):
    project_names = list()
    for iterator in arr_paths:
        iterator_splitted = iterator.split('/')
        iterator_size = len(iterator_splitted)
        project_names.append(iterator_splitted[iterator_size-1])
    
    return project_names


def get_paths_by_execution(execution):
    command_gradle = ''
    command_mvn = ''
    command_angular = ''

    if execution == 'gradle':
        command_gradle = subprocess.Popen("dir /b/s gradle", shell=True, stdout=subprocess.PIPE).stdout.read().decode('utf-8')
        command_gradle = format_path(command_gradle).splitlines()
        return command_gradle
    elif execution == 'maven':
        command_mvn = subprocess.Popen("dir /b/s mvnw", shell=True, stdout=subprocess.PIPE).stdout.read().decode('utf-8')
        command_mvn = format_path(command_mvn).splitlines()
        return command_mvn
    elif execution == 'angular':
        command_angular = subprocess.Popen("dir /b/s angular.json", shell=True, stdout=subprocess.PIPE).stdout.read().decode('utf-8')
        command_angular = format_path(command_angular).splitlines()
        return command_angular
    else:
        print('no execution founded')

    return []


def generate_projects():
    for execution in executions:
        match execution:
            case 'gradle':
                gradle_projects = get_paths_by_execution('gradle')
                gradle_project_names = get_project_names(gradle_projects)
                for gradle in gradle_project_names:
                    try:
                        generate_licenses_reports('gradle', gradle)
                        report_path = f'{project_path}{gradle}/target/generated-resources/licenses.xml'
                        handler('gradle', report_path)
                    except Exception as e:
                        print(f'Something else went wrong generating {maven} project')
                        print(e)

            case 'maven':
                maven_projects = get_paths_by_execution('maven')
                maven_project_names = get_project_names(maven_projects)
                for maven in maven_project_names:
                    try:
                        generate_licenses_reports('maven', maven)
                        report_path = f'{project_path}{maven}/target/generated-resources/licenses.xml'
                        handler('maven', report_path)
                    except Exception as e:
                        print(f'Something else went wrong generating {maven} project')
                        print(e)

            case 'angular':
                angular_projects = get_paths_by_execution('angular')
                angular_project_names = get_project_names(angular_projects)
                for angular in angular_project_names:
                    try:
                        generate_licenses_reports('angular', angular)
                        report_path = f'{project_path}{angular}/licenses.json'
                        handler('angular', report_path)
                    except Exception as e:
                        print(f'Something else went wrong generating {angular} project')
                        print(e)
        
