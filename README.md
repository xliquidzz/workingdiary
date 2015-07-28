*WorkingDiary*
======

*Copyright (C) 2015 CSS Versicherung*

This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.

# Technology

## Server

We use [Dropwizard](http://www.dropwizard.io), a simple and leight-weight Java framework for developing ops-friendly, high-performance, RESTful web services. The database of our choice is [H2](http://h2database.com/), which uses a database file in production mode and an in memory database for integration tests. [Liquibase](http://www.liquibase.org/) is responsible for populating and migrating the database schema. For testing purposes we are using [JUnit](http://junit.org/), [AssertJ](http://joel-costigliola.github.io/assertj/), [Mockito](http://mockito.org/) and [PowerMock](https://code.google.com/p/powermock/). Everything is tied together using a [Gradle](http://gradle.org/) build.

## Client

For the client we decided wo go with [AngularJS](https://angularjs.org/) and [TypeScript](http://www.typescriptlang.org/). In the next weeks we need to decide which technology we'll use for testing the client and if we want to use a UI framework and/or an icon library.

## Tools

We use a [Gradle](http://gradle.org/) build to tie everything together. As a result this project is IDE independent (every state-of-the-art IDE should be able to import and/or use a Gradle project).

# How to contribute to *WorkingDiary*

## Keep your fork in sync

If you fork this repository, GitHub will not keep your fork in sync with this repository. You have to do it on your own.

1. If not already done, add this repository as an upstream to your repository:<br/>`git remote add upstream https://github.com/css-ch/workingdiary.git`
2. Verify that this repository was added successfully:<br/>`git remote -v`
3. Fetch branches and commits from this repository to your local branch `upstream/master`:<br/>`git fetch upstream`
4. If you are not on your local master branch, check it out:<br/>`git checkout master`
5. Merge the changes from this repository into your repository:<br/>`git merge upstream/master`
7. Push your updated repository to your GitHub fork:<br/>`git push origin master`

## Frequently Asked Questions

1. When I try to push, I get a `non-fast-forward updates were rejected` error.<br/>*Your local copy of a repository is out of sync with, or behind the upstream repository, you are pushing to. You must retrieve the upstream changes, before you are able to push your local changes.*
