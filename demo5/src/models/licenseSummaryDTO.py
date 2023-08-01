from src.models.dependenciesDTO import dependencies

class licenseSummary:

    def __init__(self, dependencies: dependencies) -> None:
        self.dependencies = dependencies

    def __repr__(self):
        return f"licenseSummary({self.dependencies})"