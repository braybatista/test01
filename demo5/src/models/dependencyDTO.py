import src.models.licenseDTO

class dependency:
    def __init__(self) -> None:
        self.groupId = ''
        self.artifactId = ''
        self.version = ''
        self.licenses = list()

    def __init__(self, groupId: str, artifactId: str, version: str, licenses: list()) -> None:
        self.groupId = groupId
        self.artifactId = artifactId
        self.version = version
        self.licenses = licenses

    def getGroupId(self) -> str:
        return self.groupId

    def getArtifactId(self) -> str:
        return self.artifactId

    def getVersion(self) -> str:
        return self.version

    def getLicenses(self) -> list():
        return self.licenses

    def setGroupId(self, groupId) -> None:
        self.groupId = groupId

    def setArtifactId(self, artifactId) -> None:
        self.artifactId = artifactId

    def setVersion(self, version) -> None:
        self.version = version

    def setLicenses(self, licenses) -> None:
        self.licenses = licenses

    def __repr__(self) -> str:
        return f"dependency(groupId={self.groupId}, artifactId={self.artifactId}, version={self.version}, licenses={self.licenses})"

    # def __eq__(self, other: dependency) -> bool:
    #     return self.groupId == other.groupId and self.artifactId == other.artifactId and self.version == other.version