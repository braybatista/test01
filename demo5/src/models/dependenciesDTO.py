from src.models.dependencyDTO import dependency

class dependencies:

    def __init__(self, dependency: list() = None) -> None:
        dependency = list() if (type(dependency) == 'NoneType' or dependency is None) else dependency
        self.dependency = dependency

    def getDependency(self) -> list():
        return self.dependency

    def setDependency(self, dependency: dependency) -> None:
        self.dependency.append(dependency)

    def __repr__(self):
        return f"dependencies({self.dependency})"