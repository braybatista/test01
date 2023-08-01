class license:
    def __init__(self, url: str = '', name: str = '', distribution: str = '', file: str = '') -> None:
        self.url = url
        self.name = name
        self.distribution = distribution
        self.file = file

    def getName(self) -> str:
        return self.name

    def getUrl(self) -> str:
        return self.url

    def getDistribution(self) -> str:
        return self.distribution

    def getFile(self) -> str:
        return self.file

    def setName(self, name) -> None:
        self.name = name

    def setUrl(self, url) -> None:
        self.url = url

    def setDistribution(self, distribution) -> None:
        self.distribution = distribution

    def setFile(self, file) -> None:
        self.file = file

    def __repr__(self) -> str:
        return f"license(name={self.name}, url={self.url}, distribution={self.distribution}, file={self.file})"

    # def __eq__(self, other: license) -> bool:
    #     return self.license == other.license and self.url == other.url and self.distribution == other.distribution and self.file == other.file